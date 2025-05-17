package menu.firstOption;

import menu.MainMenu;
import models.Teacher;
import service.ClearConsole;
import service.TeacherMethods;
import java.util.Scanner;

public class FirstOption {
    public static void firstOption() {
        ClearConsole.clearConsole();
        TeacherMethods.printTeachers(); // выводим список всех учителей
        Teacher theTeacher = TeacherMethods.findTeacherById(chooseTeacher()); // объект учителя с введенным id
        Diary.openDiary(theTeacher); // открываем журнал выбранного препода
    }

    private static int chooseTeacher() { // метод возвращает айди выбранного учителя
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id преподавателя, в чей электронный журнал вы хотите войти(Если хотите вернуться назад, напишите 0):\n");
        int choice = scanner.nextInt();
        if (choice == 0) MainMenu.menu();
        return choice;
    }

}
