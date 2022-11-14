package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class Course {
    private Long courseId;
    private String name;
    private String description;
    private List<Student> students;
    private static Long counter=1L;
    private Teacher teacher;

    public Course(String name, String description,Teacher teacher) {
        courseId = counter++;
        this.name = name;
        this.description = description;
        this.teacher=teacher;
        this.students = new ArrayList<>();
    }
}
