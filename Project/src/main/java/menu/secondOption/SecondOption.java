package menu.secondOption;

import menu.MainMenu;
import service.ClearConsole;
import service.GroupMethods;
import java.util.Scanner;

public class SecondOption {
    public static void menu() {
        ClearConsole.clearConsole();
        GroupMethods.printAllGroups();
        choiceGroup();
        makeChoice();
    }




    private static void makeChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nВыберите опцию: " +
                "\n1) Вывести состав другой группы" +
                "\n2) Вернуться назад");
        int choice = scanner.nextInt();
        while (choice != 1 && choice != 2) {
            System.out.println("Вы ввели номер несуществующей опции" +
                    "\n Введите номер заново: ");
            choice = scanner.nextInt();
        }
        switch (choice) {
            case 1:
                menu();
                break;
            case 2:
                MainMenu.menu();
                break;
        }
    }

    private static void choiceGroup() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id группы из списка: ");
        int group_id = scanner.nextInt();
        while (group_id < 0 || group_id > 7) {
            System.out.println("Группы с таким id не существует, введите снова: ");
            group_id = scanner.nextInt();
        }
        ClearConsole.clearConsole();
        GroupMethods.printTheGroup(group_id);
    }
}
