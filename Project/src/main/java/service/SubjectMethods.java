package service;

import com.fasterxml.jackson.core.type.TypeReference;
import models.Subject;
import models.Teacher;

import java.io.File;
import java.util.List;

public class SubjectMethods {
    private static final File EXCEL_FILE = new File("C://Users/Тимур/IdeaProjects/ExcelJSON/DataBase.xlsx");
    private static final File JSON_FILE = new File("C://Users/Тимур/IdeaProjects/ExcelJSON/src/main/java/files/Subjects.json");

    public static void convertExcelToJSON() {
        List<Subject> subjects = parseSubjectsFromExcel();
        JSONMethods.writeToJSON(subjects, JSON_FILE);
    }


    public static List<Subject> readSubjectsFromJSON() {
        return JSONMethods.readFromJSON(JSON_FILE, new TypeReference<List<Subject>>() {});
    }




    private static List<Subject> parseSubjectsFromExcel() {
        return ExcelMethods.parseFromExcel(EXCEL_FILE,
                4,
                row -> new Subject(
                        (int) row.getCell(0).getNumericCellValue(),
                        row.getCell(1).getStringCellValue(),
                        (int) row.getCell(2).getNumericCellValue()
                ));
    }

}
