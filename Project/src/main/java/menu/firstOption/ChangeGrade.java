package menu.firstOption;

import models.Grade;
import models.Teacher;
import service.ClearConsole;
import service.GradeMethods;
import service.GroupMethods;
import java.util.List;
import java.util.Scanner;

public class ChangeGrade {

    public static void changeGrade(Teacher theTeacher) {
        ClearConsole.clearConsole();
        GroupMethods.choiceGroup(); // выбор группы (в этом методе мы и вводим с клавы id группы и выводим на экран все группы)
        int student_id = chooseStudent();
        ClearConsole.clearConsole();
        GradeMethods.printStudentGrades(student_id, theTeacher.getSubject_id()); // выводим оценки студента по предмету конкретного препода
        chooseAndChangeGrade(student_id, theTeacher.getSubject_id()); // выбираем оценку и изменяем ее значение и изменяем JSON файл
        makeChoice(theTeacher);
    }

    private static int chooseStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id студента из списка:");
        int student_id = scanner.nextInt();
        return student_id;
    }


    private static void chooseAndChangeGrade(int student_id, int subject_id) {
        List<Grade> grades = GradeMethods.findStudentGrades(student_id, subject_id);
        Scanner scanner = new Scanner(System.in);
        if (grades.size() == 0) {
            System.out.println("У студента нет оценок по данному предмету.");
        }
        else {
            System.out.println("Введите id оценки, которую хотите поменять: ");
            int grade_id = scanner.nextInt();
            System.out.println("Введите значение оценки, на которое вы хотите поменять существующую: ");
            int gradeValue = scanner.nextInt();
            GradeMethods.changeTheGrade(grade_id, gradeValue); // меняем оценку и записываем изменения в JSON
        }
    }


    private static void makeChoice(Teacher theTeacher) {
        ClearConsole.clearConsole();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите опцию: " +
                "\n1) Изменить оценки студентам" +
                "\n2) Вернуться назад" +
                "\n");
        int choice = scanner.nextInt();
        while (choice != 1 && choice != 2) {
            System.out.println("Вы ввели номер несуществующей опции" +
                    "\n Введите номер заново: ");
            choice = scanner.nextInt();
        }
        switch (choice) {
            case 1:
                changeGrade(theTeacher);
                break;
            case 2:
                Diary.openDiary(theTeacher);
                break;
        }
    }
}
