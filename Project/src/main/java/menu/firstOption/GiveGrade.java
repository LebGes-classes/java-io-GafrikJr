package menu.firstOption;

import models.Teacher;
import service.ClearConsole;
import service.GradeMethods;
import service.GroupMethods;
import java.util.Scanner;

public class GiveGrade {

    public static void giveGrade(Teacher theTeacher) {
        ClearConsole.clearConsole();
        GroupMethods.choiceGroup(); // выбрали и вывели группу для выбора студента
        choiceStudent(theTeacher); // выбрали студента конкретной группы
        makeChoice(theTeacher);
    }

    private static void choiceStudent(Teacher theTeacher) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id студента из списка:");
        int student_id = scanner.nextInt();
        GradeMethods.createAndAddGradeToJSON(student_id, theTeacher.getSubject_id());
    }

    private static void makeChoice(Teacher theTeacher) {
        ClearConsole.clearConsole();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите опцию: " +
                "\n1) Поставить оценки студентам" +
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
                giveGrade(theTeacher);
                break;
            case 2:
                Diary.openDiary(theTeacher);
                break;
        }
    }
}
