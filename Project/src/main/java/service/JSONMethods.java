package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class JSONMethods {

    public static <T> void writeToJSON(List<T> list, File JSON_FILE) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            objectMapper.writeValue(JSON_FILE, list);
        }
        catch (IOException e) {
            throw new RuntimeException("Не получилось сериализовать список в JSON файл по адресу " + JSON_FILE);
        }
    }


    public static <T> List<T> readFromJSON(File jsonFile, TypeReference<List<T>> typeRef) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonFile, typeRef);
        } catch (IOException e) {
            throw new RuntimeException("Не получилось десериализовать JSON файл по адресу " + jsonFile, e);
        }
    }


}
