package app.bacgradesprediction.server.utils;

public class Constants {
    public static final String TRIMESTRE_ONE = "T1";
    public static final String TRIMESTRE_TWO = "T2";
    public static final String TRIMESTRE_THREE = "T3";
    public static final String BAC = "BAC";


    public static String convertBacToCategory(float bacGrade) {
        if (bacGrade > 18) {
            return "Excellent";
        } else if (bacGrade >= 16) {
            return "Very Good";
        } else if (bacGrade >= 14) {
            return "Good";
        }  else if (bacGrade >= 12) {
            return "Acceptable";
        }else if (bacGrade >= 10) {
            return "Passable";
        }
        else {
            return "Bad";
        }
    }

}
