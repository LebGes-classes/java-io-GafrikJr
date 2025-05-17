package menu.thirdOption;

import service.ClearConsole;
import service.StudentMethods;
import java.util.Scanner;

public class AddStudent {
    public static void addStudent() {
        ClearConsole.clearConsole();
        StudentMethods.addStudentToJSON();
        makeChoice();
    }



    private static void makeChoice() {
        ClearConsole.clearConsole();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Вы добавили нового студента в институт!\n" +
                "Выберите опцию:" +
                "\n1) Добавить еще одного студента" +
                "\n2) Вернуться назад");
        int choice = scanner.nextInt();
        while (choice != 1 && choice != 2) {
            System.out.println("Вы ввели номер несуществующей опции" +
                    "\n Введите номер заново: ");
            choice = scanner.nextInt();
        }
        switch (choice) {
            case 1:
                addStudent();
                break;
            case 2:
                ThirdOption.menu();
                break;
        }
    }
}
