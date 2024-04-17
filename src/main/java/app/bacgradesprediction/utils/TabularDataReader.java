package app.bacgradesprediction.utils;

import app.bacgradesprediction.models.StudentRecord;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.io.FileInputStream;
import java.util.Iterator;
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
    private List<StudentRecord> readCSV(String filePath) {
        List<StudentRecord> records = new ArrayList<>();
        try (CSVParser parser = new CSVParser(new FileReader(filePath), CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            for (CSVRecord record : parser) {
                float grade1 = Float.parseFloat(record.get("S1"));
                float grade2 = Float.parseFloat(record.get("S2"));
                float grade3 = Float.parseFloat(record.get("S3"));
                float bac = Float.parseFloat(record.get("BAC"));
                records.add(new StudentRecord(grade1, grade2, grade3,bac));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    private List<StudentRecord> readExcel(String filePath) {
        List<StudentRecord> records = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            rows.next(); // Skip header row

            while (rows.hasNext()) {
                Row row = rows.next();

                float grade1 = (float) row.getCell(0).getNumericCellValue();
                float grade2 = (float) row.getCell(1).getNumericCellValue();
                float grade3 = (float) row.getCell(2).getNumericCellValue();
                float bac = (float) row.getCell(2).getNumericCellValue();
                records.add(new StudentRecord( grade1, grade2, grade3,bac));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

}
