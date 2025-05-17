package service;

import com.fasterxml.jackson.core.type.TypeReference;
import models.Group;
import models.Student;

import java.io.File;
import java.util.*;


public class GroupMethods {
    private static final File EXCEL_FILE = new File("C://Users/Тимур/IdeaProjects/ExcelJSON/DataBase.xlsx");
    private static final File JSON_FILE = new File("C://Users/Тимур/IdeaProjects/ExcelJSON/src/main/java/files/Groups.json");


    public static void convertExcelToJSON() {
        List<Group> groups = parseGroupsFromExcel();
        JSONMethods.writeToJSON(groups, JSON_FILE);
    }


    public static List<Group> readGroupsFromJSON() {
        return JSONMethods.readFromJSON(JSON_FILE, new TypeReference<List<Group>>() {});
    }


    public static void printAllGroups() {
        List<Group> groups = readGroupsFromJSON();
        for (int i = 0; i < groups.size(); i++) {
            System.out.println((i + ") Группа " + groups.get(i).getNumber()));
        }
    }


    public static String getNumOfGroup(int group_id) {
        String groupNumber = "";
        List<Group> groups = readGroupsFromJSON();
        for (Group group : groups) {
            if (group.getId() == group_id) {
                groupNumber = group.getNumber();
            }
        }
        return groupNumber;
    }


    public static List<Student> getStudentsOfGroup(int group_id) {
        List<Student> students = StudentMethods.readStudentsFromJSON();
        List<Group> groups = readGroupsFromJSON();
        return findStudentsByGroupId(group_id);
    }


    public static void printTheGroup(int group_id) {
        List<Student> students = getStudentsOfGroup(group_id);
        List<String> FIOs = new ArrayList<>();

        for (Student student : students) {
            FIOs.add("id: "+ student.getId() + ") " + student.getFio());
        }
        Collections.sort(FIOs);

        for (String fio : FIOs) {
            System.out.println(fio);
        }
    }




    private static List<Group> parseGroupsFromExcel() {
        return ExcelMethods.parseFromExcel(EXCEL_FILE,
                5,
                row -> new Group(
                        (int) row.getCell(0).getNumericCellValue(),
                        row.getCell(1).getStringCellValue()
                ));
    }


    private static List<Student> findStudentsByGroupId(int group_id) {
        List<Student> students = StudentMethods.readStudentsFromJSON();
        List<Student> theGroupStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getGroup_id() == group_id) theGroupStudents.add(student);
        }
        return theGroupStudents;
    }

    public static void choiceGroup() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id группы из списка:");
        GroupMethods.printAllGroups();
        int group_id = scanner.nextInt();
        while (group_id == 0) {
            System.out.println("Вы не можете поставить оценки студентам в академическом отпуске" +
                    "\nНапишите id группы еще раз: " +
                    "\n");
            group_id = scanner.nextInt();
        }
        ClearConsole.clearConsole();
        GroupMethods.printTheGroup(group_id);
    }

}
