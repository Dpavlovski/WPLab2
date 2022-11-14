package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Repository;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CourseRepository {
    public List<Course> findAllCourses(){
        return DataHolder.courses.stream().sorted(Comparator.comparing(Course::getName)).collect(Collectors.toList());
    }

    public Course findById (Long courseId) {
       return DataHolder.courses.stream().filter(s->s.getCourseId().equals(courseId)).findFirst().orElse(null);
    }

    public List<Student> findAllStudentsByCourse(Long courseId) {
       Course course= DataHolder.courses.stream().filter(c->c.getCourseId().equals(courseId)).findFirst().orElse(null);
       if(course!=null)
           return course.getStudents();
       return null;
    }

    public Course addStudentToCourse(Student student, Course course) {
        if(student!=null && DataHolder.courses.contains(course)) {
            int id= DataHolder.courses.indexOf(course);
            DataHolder.courses.get(id).getStudents().add(student);
        }
        return course;
    }

    public Course findByName (String name) {
        return DataHolder.courses.stream().filter(s->s.getName().equals(name)).findFirst().orElse(null);
    }
    public Course addCourse(String name,String description,Teacher teacher){
       if(name==null || name.isEmpty() || description==null || description.isEmpty() || teacher==null || findByName(name)!=null)
           throw new IllegalArgumentException();
       DataHolder.courses.removeIf(c->c.getName().equals(name));
       Course course=new Course(name,description,teacher);
       DataHolder.courses.add(course);
       return course;
    }

    public void removeCourse(Long id) {
        DataHolder.courses.removeIf(c->c.getCourseId().equals(id));
    }
}