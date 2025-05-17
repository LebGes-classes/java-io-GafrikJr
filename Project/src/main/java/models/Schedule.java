package models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import service.GroupMethods;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"id", "group_id", "teacher_id", "startTime", "endTime", "weekDay"})


public class Schedule {
    private int id;
    private int group_id;
    private int teacher_id;
    private String startTime;
    private String endTime;
    private String weekDay;

    public Schedule(int id, int group_id, int teacher_id, String startTime, String endTime, String weekDay) {
        this.id = id;
        this.group_id = group_id;
        this.teacher_id = teacher_id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.weekDay = weekDay;
    }

    public void printLessonInfo() {
        System.out.println("День: " + weekDay +
                "| Начало: " + startTime +
                "| Конец: " + endTime +
                "| Группа: " + GroupMethods.getNumOfGroup(group_id)
        );
    }



}
