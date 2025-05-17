package menu.thirdOption;

import models.Student;
import service.ClearConsole;
import service.StudentMethods;

import java.util.Scanner;

public class KickStudent {

    public static void kickStudent() {
        ClearConsole.clearConsole();
        StudentMethods.printStudents();
        kickTheStudent();
        makeChoice();
    }


    private static void kickTheStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id студента, которого хотите исключить:\n");
        int id = scanner.nextInt();
        Student student = StudentMethods.deleteStudentFromJSON(id);
        ClearConsole.clearConsole();
        System.out.println("Вы исключили " + student.getFio() + " из группы " + student.getGroup());
    }


    private static void makeChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(
                "Выберите опцию:" +
                "\n1) Выгнать еще одного студента" +
                "\n2) Вернуться назад");
        int choice = scanner.nextInt();
        while (choice != 1 && choice != 2) {
            System.out.println("Вы ввели номер несуществующей опции" +
                    "\n Введите номер заново: ");
            choice = scanner.nextInt();
        }
        switch (choice) {
            case 1:
                kickStudent();
                break;
            case 2:
                ThirdOption.menu();
                break;
        }
    }
}
