package service;

import com.fasterxml.jackson.core.type.TypeReference;
import models.Student;
import java.io.*;
import java.util.List;
import java.util.Scanner;

public class StudentMethods {
    private static final File EXCEL_FILE = new File("C://Users/Тимур/IdeaProjects/ExcelJSON/DataBase.xlsx");
    private static final File JSON_FILE = new File("C://Users/Тимур/IdeaProjects/ExcelJSON/src/main/java/files/Students.json");


    public static void convertExcelToJSON() {
        List<Student> students = parseStudentsFromExcel();
        JSONMethods.writeToJSON(students, JSON_FILE);
    }

    public static void addStudentToJSON() {
        List<Student> students = readStudentsFromJSON();
        students.add(createStudentFromInput(students));
        JSONMethods.writeToJSON(students, JSON_FILE);
    }

    public static void convertStudentsJSONToExcel() {
        ExcelMethods.convertJSONToExcel(
                JSON_FILE,
                EXCEL_FILE,
                1,
                new TypeReference<List<Student>>() {},
                (student, row) -> {
                    row.createCell(0).setCellValue(student.getId());
                    row.createCell(1).setCellValue(student.getFio());
                    row.createCell(2).setCellValue(student.getGroup_id());
                    row.createCell(3).setCellValue(student.getGroup());
                        }
                );
    }

    public static List<Student> readStudentsFromJSON() {
        return JSONMethods.readFromJSON(JSON_FILE, new TypeReference<List<Student>>() {});
    }

    public static Student deleteStudentFromJSON(int id) {
        List<Student> students = readStudentsFromJSON();
        Student kicked = findStudentById(id, students);
        students.remove(kicked);
        JSONMethods.writeToJSON(students, JSON_FILE);
        return kicked;
    }

    public static void transferTheStudent(int id, int group_id) {
        // сейчас надо обновить джсон(то есть не добавлять новый объект, а изменить существующий)
        List<Student> students = readStudentsFromJSON();
        for (Student student : students) {
            if (student.getId() == id) {
                student.setGroup_id(group_id);
                student.setGroup(GroupMethods.getNumOfGroup(group_id));
            }
        }
        JSONMethods.writeToJSON(students, JSON_FILE);
    }


    private static List<Student> parseStudentsFromExcel() {
        return ExcelMethods.parseFromExcel(EXCEL_FILE,
                1,
                row -> new Student(
                        (int) row.getCell(0).getNumericCellValue(),
                        row.getCell(1).getStringCellValue(),
                        (int) row.getCell(2).getNumericCellValue(),
                        row.getCell(3).getStringCellValue()
                ));
    }

    private static Student createStudentFromInput(List<Student> students) {
        System.out.println("Введите последовательно ФИО и id группы: \n");
        Scanner scanner = new Scanner(System.in);
        String fio = scanner.nextLine();
        int group_id = scanner.nextInt();

        Student student = new Student(
                students.get(students.size() - 1).getId() + 1,
                fio,
                group_id,
                GroupMethods.getNumOfGroup(group_id)
        );
        return student;
    }

    private static Student findStudentById(int id, List<Student> students) {
        Student theStudent = null;
        // ищем среди объектов из JSON файла объект с id = введенному
        for (Student student : students) {
            if (student.getId() == id) {
                theStudent = student;
            }
        }
        return theStudent;
    }

    public static void printStudents() {
        List<Student> students = readStudentsFromJSON();
        for (Student student : students) {
            student.printInfo();
        }
    }
}
