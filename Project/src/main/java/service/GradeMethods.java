package service;

import com.fasterxml.jackson.core.type.TypeReference;
import models.Grade;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GradeMethods {
    private static final File EXCEL_FILE = new File("C://Users/Тимур/IdeaProjects/ExcelJSON/DataBase.xlsx");
    private static final File JSON_FILE = new File("C://Users/Тимур/IdeaProjects/ExcelJSON/src/main/java/files/Grades.json");


    public static void convertExcelToJSON() {
        List<Grade> grades = parseGradesFromExcel();
        JSONMethods.writeToJSON(grades, JSON_FILE);
    }


    public static void changeTheGrade(int grade_id, int gradeValue) {
        // сейчас надо обновить джсон(то есть не добавлять новый объект, а изменить существующий)
        List<Grade> grades = readGradesFromJSON();
        for (Grade grade : grades) {
            if (grade.getId() == grade_id) {
                grade.setGrade(gradeValue);
            }
        }
        JSONMethods.writeToJSON(grades, JSON_FILE);
    }


    private static Grade giveGrade(int student_id, int subject_id, List<Grade> grades) { // потом будет так: в менюшке мы выбираем учителя -> предмет -> студента, и ему уже ставим оценку. Поля student_id и т.д сохраняются по мере продвижения по менюшке
        Scanner scanner = new Scanner(System.in);
        ClearConsole.clearConsole();
        System.out.println("Введите оценку, которую хотите поставить: ");
        int gradeValue = scanner.nextInt();
        while (gradeValue < 2 || gradeValue > 5) {
            System.out.println("Поставьте оценку по 5-балльной шкале: \n");
            gradeValue = scanner.nextInt();
        }
        Grade grade = new Grade(
                grades.get(grades.size() - 1).getId() + 1,
                student_id,
                subject_id,
                gradeValue
        );
        return grade;
    }



    public static void createAndAddGradeToJSON(int student_id, int subject_id) { // этот метод вызывается вместе с giveGrade
        List<Grade> grades = readGradesFromJSON();
        grades.add(giveGrade(student_id, subject_id, grades));
        JSONMethods.writeToJSON(grades, JSON_FILE);
    }


    public static void convertGradesJSONToExcel() {
        ExcelMethods.convertJSONToExcel(
                JSON_FILE,
                EXCEL_FILE,
                3,
                new TypeReference<List<Grade>>() {},
                (grade, row) -> {
                    row.createCell(0).setCellValue(grade.getId());
                    row.createCell(1).setCellValue(grade.getStudent_id());
                    row.createCell(2).setCellValue(grade.getSubject_id());
                    row.createCell(3).setCellValue(grade.getGrade());
                }
        );
    }


    public static List<Grade> readGradesFromJSON() {
        return JSONMethods.readFromJSON(JSON_FILE, new TypeReference<List<Grade>>() {});
    }


    public static List<Grade> findStudentGrades(int student_id, int subject_id) {
        List<Grade> grades = GradeMethods.readGradesFromJSON();
        List<Grade> theStudentGrades = new ArrayList<>();
        for (Grade grade : grades) {
            if (grade.getStudent_id() == student_id && grade.getSubject_id() == subject_id) {
                theStudentGrades.add(grade);
            }
        }
        return theStudentGrades;
    }


    public static void printStudentGrades(int student_id, int subject_id) {
        List<Grade> grades = findStudentGrades(student_id, subject_id);
        for (Grade grade : grades) {
            if (grade.getSubject_id() == subject_id) {
                grade.printInfo();
            }
        }
    }



    private static List<Grade> parseGradesFromExcel() {
        return ExcelMethods.parseFromExcel(EXCEL_FILE,
                3,
                row -> new Grade(
                        (int) row.getCell(0).getNumericCellValue(),
                        (int) row.getCell(1).getNumericCellValue(),
                        (int) row.getCell(2).getNumericCellValue(),
                        (int) row.getCell(3).getNumericCellValue()
                ));
    }

}
