package menu;

import menu.firstOption.FirstOption;
import menu.secondOption.SecondOption;
import menu.thirdOption.ThirdOption;
import service.*;
import java.util.Scanner;

public class MainMenu {

    public static void menu() {
        ClearConsole.clearConsole();
        System.out.println(
                "Выберите опцию:" +
                        "\n1) Выбрать преподавателя" +
                        "\n2) Вывести список группы" +
                        "\n3) Изменить состав группы" +
                        "\n4) Закрыть программу" +
                        "\n"
        );
        makeChoice();
    }


    private static void makeChoice() {
        Scanner scanner = new Scanner(System.in);

        int choice = scanner.nextInt();
        while(choice != 1 && choice != 2 && choice != 3 && choice != 4) {
            System.out.println("Введите номер заново:\n");
            choice = scanner.nextInt();
        }
        switch(choice) {
            case 1:
                FirstOption.firstOption();
                break;  
            case 2:
                SecondOption.menu();
                break;
            case 3:
                ThirdOption.menu();
                break;
            case 4:
                StudentMethods.convertStudentsJSONToExcel();
                GradeMethods.convertGradesJSONToExcel();
                System.exit(0);
                break;
        }
    }

}
