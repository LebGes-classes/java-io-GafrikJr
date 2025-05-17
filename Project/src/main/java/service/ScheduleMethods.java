package service;

import com.fasterxml.jackson.core.type.TypeReference;
import models.Schedule;
import models.Teacher;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ScheduleMethods {
    private static final File EXCEL_FILE = new File("C://Users/Тимур/IdeaProjects/ExcelJSON/DataBase.xlsx");
    private static File JSON_FILE = new File("C://Users/Тимур/IdeaProjects/ExcelJSON/src/main/java/files/Schedule.json");

    public static void convertExcelToJSON() {
        List<Schedule> schedules = parseScheduleFromExcel();
        JSONMethods.writeToJSON(schedules, JSON_FILE);
    }

    public static List<Schedule> readScheduleFromJSON() {
        return JSONMethods.readFromJSON(JSON_FILE, new TypeReference<List<Schedule>>() {});
    }

    private static List<Schedule> findScheduleFromTeacherId(int teacher_id) {
        List<Schedule> schedule = readScheduleFromJSON();
        List<Schedule> theSchedule = new ArrayList<>();
        for (Schedule lesson : schedule) {
            if (lesson.getTeacher_id() == teacher_id) {
                theSchedule.add(lesson);
            }
        }
        return theSchedule;
    }

    public static void printScheduleFromTeacherId(int teacher_id) {
        List<Schedule> theSchedule = findScheduleFromTeacherId(teacher_id);
        for (Schedule lesson : theSchedule) {
            lesson.printLessonInfo();
        }
    }

    private static List<Schedule> parseScheduleFromExcel() {
        return ExcelMethods.parseFromExcel(EXCEL_FILE,
                2,
                row -> new Schedule(
                        (int) row.getCell(0).getNumericCellValue(),
                        (int) row.getCell(1).getNumericCellValue(),
                        (int) row.getCell(2).getNumericCellValue(),
                        row.getCell(3).getStringCellValue(),
                        row.getCell(4).getStringCellValue(),
                        row.getCell(5).getStringCellValue()
                ));
    }

}
