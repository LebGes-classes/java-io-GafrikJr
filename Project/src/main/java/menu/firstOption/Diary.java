package menu.firstOption;

import models.Teacher;
import service.ClearConsole;
import java.util.Scanner;

public class Diary {

    public static void openDiary(Teacher theTeacher) {
        ClearConsole.clearConsole();
        printOptions(); // выводим возможные опции
        makeChoice(theTeacher); // делаем выбор опции, передавая в качестве параметра объект препода
    }


    private static void printOptions() {
        System.out.println("Выберите опцию: " +
                "\n1) Поставить оценку" +
                "\n2) Изменить оценку" +
                "\n3) Вывести ваше расписание на неделю" +
                "\n4) Вывести оценки группы" +
                "\n5) Вернуться назад");
    }


    private static void makeChoice(Teacher theTeacher) {
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        while (choice < 1 || choice > 5) {
            System.out.println("Введите номер заново:\n");
            choice = scanner.nextInt();
        }
        switch (choice) {
            case 1:
                GiveGrade.giveGrade(theTeacher);
                break;
            case 2:
                ChangeGrade.changeGrade(theTeacher);
                break;
            case 3:
                PrintSchedule.printSchedule(theTeacher);
                break;
            case 4:
                PrintGroupGrades.printGroupGrades(theTeacher);
                break;
            case 5:
                FirstOption.firstOption();
                break;
        }
    }
}
