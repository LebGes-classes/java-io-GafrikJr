package models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
// следующая аннотация нужна, для того чтобы в json поля объекта сохранялись строго в таком порядке
@JsonPropertyOrder({"id", "fio"})

public class Teacher {
    private int id;
    private String fio;
    private int subject_id;
    private String subject;

    public Teacher(int id, String fio, int subject_id, String subject) {
        this.id = id;
        this.fio = fio;
        this.subject_id = subject_id;
        this.subject = subject;
    }

    public void printInfo() {
        System.out.println("id: " + id + "| ФИО: " + fio + "| id предмета: " + subject_id + "| предмет: " + subject);
    }

}
