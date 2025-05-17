package org.example;

import menu.MainMenu;
import service.ClearConsole;
import service.ExcelMethods;

public class Main {
    public static void main(String[] args) {
        ClearConsole.clearConsole();
        ExcelMethods.convertAllExcels();
        MainMenu.menu();
    }
}