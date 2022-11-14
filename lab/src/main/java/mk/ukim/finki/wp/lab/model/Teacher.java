package mk.ukim.finki.wp.lab.model;

import lombok.Data;
@Data
public class Teacher {
    private Long id;
    private String name;
    private String surname;
    private static Long counter=0L;

    public Teacher( String name, String surname) {
        id = counter++;
        this.name = name;
        this.surname = surname;
    }
}
