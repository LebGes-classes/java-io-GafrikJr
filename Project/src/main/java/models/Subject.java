package models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonPropertyOrder({"id", "name", "teacher_id"})

public class Subject {
    private int id;
    private String name;
    private int teacher_id;

    public Subject(int id, String name, int teacher_id) {
        this.id = id;
        this.name = name;
        this.teacher_id = teacher_id;
    }
}
