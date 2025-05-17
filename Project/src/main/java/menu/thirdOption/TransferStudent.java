package menu.thirdOption;

import service.ClearConsole;
import service.StudentMethods;
import java.util.Scanner;

public class TransferStudent {

    public static void transferStudent() {
        ClearConsole.clearConsole();
        StudentMethods.printStudents();
        transferChoosenStudent(); // переводим студента и записываем в JSON
        makeChoice();
    }

    public static void transferChoosenStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id студента, которого хотите перевести в другую группу:\n");
        int id = scanner.nextInt();
        System.out.println("Введите id группы, в которую хотите перевести студента:\n");
        int group_id = scanner.nextInt();
        StudentMethods.transferTheStudent(id, group_id);
    }


    private static void makeChoice() {
        ClearConsole.clearConsole();
        Scanner scanner = new Scanner(System.in);
        System.out.println(
                "Выберите опцию:" +
                        "\n1) Перевести еще одного студента" +
                        "\n2) Вернуться назад");
        int choice = scanner.nextInt();
        while (choice != 1 && choice != 2) {
            System.out.println("Вы ввели номер несуществующей опции" +
                    "\n Введите номер заново: ");
            choice = scanner.nextInt();
        }
        switch (choice) {
            case 1:
                transferStudent();
                break;
            case 2:
                ThirdOption.menu();
                break;
        }
    }
}
