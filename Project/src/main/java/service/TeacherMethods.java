package service;

import com.fasterxml.jackson.core.type.TypeReference;
import models.Teacher;
import java.io.File;
import java.util.List;

public class TeacherMethods {
    private static final File EXCEL_FILE = new File("C://Users/Тимур/IdeaProjects/ExcelJSON/DataBase.xlsx");
    private static File JSON_FILE = new File("C://Users/Тимур/IdeaProjects/ExcelJSON/src/main/java/files/Teachers.json");

    public static void convertExcelToJSON() {
        List<Teacher> teachers = parseTeachersFromExcel();
        JSONMethods.writeToJSON(teachers, JSON_FILE);
    }

    public static List<Teacher> readTeachersFromJSON() {
        return JSONMethods.readFromJSON(JSON_FILE, new TypeReference<List<Teacher>>() {});
    }

    public static void printTeachers() {
        List<Teacher> teachers = readTeachersFromJSON();
        for (Teacher teacher : teachers) {
            teacher.printInfo();
        }
    }

    public static Teacher findTeacherById(int teacher_id) {
        Teacher theTeacher = null;
        List<Teacher> teachers = readTeachersFromJSON();
        for (Teacher teacher : teachers) {
            if (teacher.getId() == teacher_id) {
                theTeacher = teacher;
            }
        }
        return theTeacher;
    }

    private static List<Teacher> parseTeachersFromExcel() {
        return ExcelMethods.parseFromExcel(EXCEL_FILE,
                0,
                row -> new Teacher(
                        (int) row.getCell(0).getNumericCellValue(),
                        row.getCell(1).getStringCellValue(),
                        (int) row.getCell(2).getNumericCellValue(),
                        row.getCell(3).getStringCellValue()
                ));
    }
}
