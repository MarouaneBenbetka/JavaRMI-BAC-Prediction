package app.bacgradesprediction.db;
import app.bacgradesprediction.models.StudentRecord;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static app.bacgradesprediction.server.utils.Constants.*;


public class DatabaseUtil {
    private static final String URL = "jdbc:mysql://root:hellosqlworld@localhost:3306/bac_grades_prediction";
    private static final String USER = "root";
    private static final String PASS = "hellomysqlworld";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public static  void insertStudentGrades(List<Map<String, ?>> records , String schoolName) {
        String sql = "INSERT INTO StudentRecords (grade1, grade2, grade3, bacGrade, schoolName) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (Map<String, ?> record : records) {
                System.out.println(record);
                // Retrieve each field from the map using the correct keys
                float grade1 = (Float) record.get(TRIMESTRE_ONE);
                float grade2 = (Float) record.get(TRIMESTRE_TWO);
                float grade3 = (Float) record.get(TRIMESTRE_THREE);
                float bacGrade = (Float) record.get(BAC);

                // Set each parameter of the insert statement before adding to batch
                pstmt.setFloat(1, grade1);
                pstmt.setFloat(2, grade2);
                pstmt.setFloat(3, grade3);
                pstmt.setFloat(4, bacGrade);
                pstmt.setString(5, schoolName);

                pstmt.addBatch(); // Add to batch
            }

            pstmt.executeBatch(); // Execute all inserts in a batch
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
    }

    public List<Map<String, ?>> retrieveStudentGrades() {
        List<Map<String, ?>> list = new ArrayList<>();
        String query = "SELECT grade1, grade2, grade3, bacGrade, schoolName FROM StudentRecords";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Map<String, Object> map = new HashMap<>();
                map.put(TRIMESTRE_ONE, rs.getFloat("grade1"));
                map.put(TRIMESTRE_TWO, rs.getFloat("grade2"));
                map.put(TRIMESTRE_THREE, rs.getFloat("grade3"));
                map.put(BAC, rs.getFloat("bacGrade"));
                map.put("schoolName", rs.getString("schoolName"));
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
