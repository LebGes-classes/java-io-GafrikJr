package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.function.BiConsumer;
import java.util.function.Function;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelMethods {

    public static <T> List<T> parseFromExcel(File excelFile, int sheetIndex, Function<Row, T> rowParser) {
        List<T> list = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(excelFile)) {
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            int i = 1;
            while (i <= sheet.getLastRowNum() && (sheet.getRow(i).getCell(0).getCellType() != null) && (sheet.getRow(i).getCell(0).getCellType() != CellType.STRING)) {
                Row row = sheet.getRow(i);
                T item = rowParser.apply(row); // функциональный интерфейс для перехода от строки из Excel к объекту типа T
                list.add(item);
                i++;
            }
            return list;
        }
        catch (IOException | InvalidFormatException e) {
            throw new RuntimeException("Ошибка при чтении Excel-файла: " + excelFile.getAbsolutePath() + e);
        }
    }


    public static <T> void convertJSONToExcel(
            File jsonFile,
            File excelFile,
            int sheetIndex,
            TypeReference<List<T>> typeRef,
            BiConsumer<T, Row> rowWriter
    ) {
        // получаем список объектов T типа из соответствующего JSON файла
        List<T> list = JSONMethods.readFromJSON(jsonFile, typeRef);
        try (FileInputStream fis = new FileInputStream(excelFile);
             Workbook workbook = new XSSFWorkbook(fis);
             FileOutputStream fos = new FileOutputStream(excelFile))
        {
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            clearSheet(sheet); // чистим таблицу Excel от всех записей чтобы все заново записать
            ExcelMethods.fillingSheet(list, sheet, rowWriter); // заполняем таблицу с помощью функционального интерфейса BiConsumer

            workbook.write(fos); // записываем в Excel книгу изменения
        } catch (IOException e) {
            throw new RuntimeException("Не удалось записать данные в Excel", e);
        }
    }




    private static <T> void fillingSheet(List<T> list, Sheet sheet, BiConsumer<T, Row> rowWriter) {
        int curRow = 1;
        for (T item : list) {
            Row row = sheet.createRow(curRow++);
            rowWriter.accept(item, row); // функциональный интерфейс, реализуется в convertGradesJSONToExcel
        }
    }


    private static void clearSheet(Sheet sheet) {
        // очищаем все строки на случай, если мы удалим запись из базы данных, чтобы записать всё заново подряд без пробелов
        int curRow = sheet.getLastRowNum();
        while (curRow > 0) {
            for (Cell cell : sheet.getRow(curRow)) {
                cell.setCellValue("");
            }
            curRow--;
        }
    }

    public static void convertAllExcels() {
        StudentMethods.convertExcelToJSON();
        GroupMethods.convertExcelToJSON();
        GradeMethods.convertExcelToJSON();
        ScheduleMethods.convertExcelToJSON();
        SubjectMethods.convertExcelToJSON();
        TeacherMethods.convertExcelToJSON();
    }
}
