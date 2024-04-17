package app.bacgradesprediction.utils;

import app.bacgradesprediction.models.StudentRecord;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import java.io.FileInputStream;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;

public class TabularDataReader {

    private final List<String> excelExtensions = Arrays.asList("xlsx", "xlx", "xls") ;
    private final List<String> csvExtensions = Arrays.asList("csv", "txt") ;

    public List<StudentRecord> readTabularData(String filepath) throws Exception {
        String extension = FilenameUtils.getExtension(filepath);

        if (excelExtensions.contains(extension))
            return readExcel(filepath);

        if (csvExtensions.contains(extension))
            return readCSV(filepath);

        throw new Exception("Extension not valid.");

    };

    public List<Map<String,?>> records2Maps(List<StudentRecord> studentRecords){
        return studentRecords.stream().map(StudentRecord::toMap).collect(Collectors.toList());
    }

    private List<StudentRecord> readCSV(String filePath) {
        List<StudentRecord> records = new ArrayList<>();
        try (CSVParser parser = new CSVParser(new FileReader(filePath), CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            for (CSVRecord record : parser) {
                ArrayList<Float> grades = new ArrayList<>();

                // Attempt to parse each grade and handle exceptions
                grades.add(parseGrade(record.get("S1")));
                grades.add(parseGrade(record.get("S2")));
                // Default grade3 to 0.0f if missing
                String grade3Value = record.get("S3");
                grades.add(grade3Value.isEmpty() ? 0.0f : parseGrade(grade3Value));

                // Attempt to parse BAC, default to null if missing
                Float bac = parseGrade(record.get("BAC"));

                // Add to records if all required fields are present and not null
                if (!anyValueIsNull(grades, bac) && grades.size() == 3) {
                    records.add(new StudentRecord(grades, bac));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    public List<StudentRecord> readExcel(String filePath) {
        List<StudentRecord> records = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            rows.next(); // Skip header row

            while (rows.hasNext()) {
                Row row = rows.next();
                ArrayList<Float> grades = new ArrayList<>();

                for (int i = 0; i < 3; i++) { // Assuming 3 grades to collect
                    Cell cell = row.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    if (cell != null) {
                        try {
                            grades.add((float) ((Cell) cell).getNumericCellValue());
                        } catch (IllegalStateException | NumberFormatException e) {
                            System.err.println("Error reading cell value, setting default value to 0");
                            grades.add(null); // defaulting to 0 if there's a reading error
                        }
                    } else {
                        grades.add(i == 2 ? 0.0f : null); // Specific logic for grade3
                    }
                }

                // Assume the BAC is in the 3rd cell; replace with appropriate logic or cell index
                Cell bacCell = row.getCell(3, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

                Float bac = (bacCell != null) ? (float) bacCell.getNumericCellValue() : null;

                if (!anyValueIsNull(grades,bac) && grades.size() == 3)
                    records.add(new StudentRecord(grades,bac));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    private Float parseGrade(String value) {
        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private boolean anyValueIsNull(List<Float> grades, Float bac) {
        // Check each grade in the list
        for (Float grade : grades) {
            if (grade == null) {
                return true; // Return true if any grade is null
            }
        }

        // Check the BAC value
        if (bac == null) {
            return true; // Return true if BAC is null
        }

        // Return false if none are null
        return false;
    }



}
