package menu.thirdOption;

import menu.MainMenu;
import service.ClearConsole;
import java.util.Scanner;

public class ThirdOption {

    public static void menu() {
        ClearConsole.clearConsole();
        Scanner scanner = new Scanner(System.in);
        System.out.println(
                "Выберите опцию:" +
                        "\n1) Добавить студента в группу" +
                        "\n2) Выгнать студента" +
                        "\n3) Перевести студента в другую группу" +
                        "\n4) Вернуться назад"
        );
        int choice = scanner.nextInt();
        while (choice < 1 || choice > 4) {
            System.out.println("Введите номер заново:\n");
            choice = scanner.nextInt();
        }
        switch(choice) {
            case 1:
                AddStudent.addStudent();
                break;
            case 2:
                KickStudent.kickStudent();
                break;
            case 3:
                TransferStudent.transferStudent();
                break;
            case 4:
                MainMenu.menu();
                break;
        }
    }
}
