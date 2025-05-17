package models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@JsonPropertyOrder({"id", "student_id", "subject_id", "grade"})

public class Grade {
    private int id;
    private int student_id;
    private int subject_id;
    private int grade;

    public Grade(int id, int student_id, int subject_id, int grade) {
        this.id = id;
        this.student_id = student_id;
        this.subject_id = subject_id;
        this.grade = grade;
    }

    public void printInfo() {
        System.out.println("id: " + id + "| оценка: " + grade);
    }
}
