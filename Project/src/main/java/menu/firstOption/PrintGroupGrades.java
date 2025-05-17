package menu.firstOption;

import models.Grade;
import models.Student;
import models.Teacher;
import service.ClearConsole;
import service.GradeMethods;
import service.GroupMethods;
import java.util.List;
import java.util.Scanner;

public class PrintGroupGrades {
    public static void printGroupGrades(Teacher theTeacher) {
        ClearConsole.clearConsole();
        Scanner scanner = new Scanner(System.in);
        GroupMethods.printAllGroups(); // просто вывел группы на экран
        System.out.println("Введите id группы из списка:");
        int groupId = scanner.nextInt();
        List<Student> students = GroupMethods.getStudentsOfGroup(groupId); // список из студентов группы
        ClearConsole.clearConsole();
        // пробегаемся по студентам и получаем список их оценок
        for (Student student : students) {
            List<Grade> grades = GradeMethods.findStudentGrades(student.getId(), theTeacher.getSubject_id());
            System.out.print(student.getFio() + ": ");
            for (Grade grade : grades) {
                System.out.print(grade.getGrade() + ", ");
            }
            System.out.println(" ");
        }

        System.out.println("\nЧтобы вывести оценки другой группы - напишите 0" +
                "\nЧтобы вернуться в прошлое меню - напишите 1" +
                "\n");
        int choice = scanner.nextInt();
        switch (choice) {
            case 0:
                printGroupGrades(theTeacher);
                break;
            case 1:
                Diary.openDiary(theTeacher);
                break;
        }
    }



}
