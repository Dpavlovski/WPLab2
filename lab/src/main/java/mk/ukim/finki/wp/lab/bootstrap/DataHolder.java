package mk.ukim.finki.wp.lab.bootstrap;

import lombok.Getter;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataHolder {
    public static List<Student> students=new ArrayList<>();
    public static List<Course> courses=new ArrayList<>();
    public static List<Teacher> teachers=new ArrayList<>();

    @PostConstruct
    public void init() {
        students.add(new Student("petar.petrov","petarPetrov123","Петар","Петров",false));
        students.add(new Student("mile.milev","mileMilev123","Миле","Милев",false));
        students.add(new Student("gjorgji.gjorgjiev","gjorgiGjorgiev123","Ѓорѓи","Ѓорѓиев",false));
        students.add(new Student("riste.ristev","risteRistev123","Ристе","Ристев",false));
        students.add(new Student("pavle.pavlov","pavlePavlov123","Павле","Павлов",false));

        teachers.add(new Teacher("Riste","Stojanov"));
        teachers.add(new Teacher("Saso","Gramatikov"));
        teachers.add(new Teacher("Dimitar","Trajanov"));
        teachers.add(new Teacher("Ana","Todoroska"));
        teachers.add(new Teacher("Kostadin","Misev"));

        courses.add(new Course("Веб програмирање","JavaSpringBoot",teachers.get(0)));
        courses.add(new Course("Оперативни системи","Putty",teachers.get(1)));
        courses.add(new Course("Електронска и мобилна трговија","Android",teachers.get(2)));
        courses.add(new Course("Компјутерски мрежи","GNS3",teachers.get(3)));
        courses.add(new Course("Објектно-ориентирано програмирање","Codeblocks",teachers.get(4)));
    }
}
