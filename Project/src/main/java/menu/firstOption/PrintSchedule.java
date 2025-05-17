package menu.firstOption;

import models.Teacher;
import service.ClearConsole;
import service.ScheduleMethods;

import java.util.Scanner;

public class PrintSchedule {
    public static void printSchedule(Teacher theTeacher) {
        ClearConsole.clearConsole();
        ScheduleMethods.printScheduleFromTeacherId(theTeacher.getId()); // вывод расписания препода
        makeChoice(theTeacher);
    }

    private static void makeChoice(Teacher theTeacher) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Чтобы вернуться назад, напишите 0:");
        int choice = scanner.nextInt();
        while (choice != 0) {
            System.out.println("Вы ввели неверное число!");
            choice = scanner.nextInt();
        }
        Diary.openDiary(theTeacher);
    }
}
