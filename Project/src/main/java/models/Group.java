package models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
// следующая аннотация нужна, для того чтобы в json поля объекта сохранялись строго в таком порядке
@JsonPropertyOrder({"id", "number"})
public class Group {
    private int id;
    private String number;

    public Group(int id, String number) {
        this.id = id;
        this.number = number;
    }

    public void printInfo() {
        System.out.println("id: " + id + "\nназвание группы: " + number);
    }

    public int getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }
}
