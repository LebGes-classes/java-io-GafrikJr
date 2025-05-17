package models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
// следующая аннотация нужна, для того чтобы в json поля объекта сохранялись строго в таком порядке
@JsonPropertyOrder({"id", "fio", "group_id"})
public class Student {
    private int id;
    private String fio;
    private int group_id;
    private String group;

    public Student(int id, String FIO, int group_id, String group) {
        this.id = id;
        this.fio = FIO;
        this.group_id = group_id;
        this.group = group;
    }

    public void printInfo() {
        System.out.println("id: " + id +
                "| ФИО: " + fio +
                "| Группа: " + group);
    }


}
