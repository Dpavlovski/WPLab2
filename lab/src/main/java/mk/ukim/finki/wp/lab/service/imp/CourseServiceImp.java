package mk.ukim.finki.wp.lab.service.imp;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.repository.StudentRepository;
import mk.ukim.finki.wp.lab.repository.TeacherRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImp implements CourseService {
    private CourseRepository courseRepository;
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;

    public CourseServiceImp(CourseRepository courseRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        return courseRepository.findAllStudentsByCourse(courseId);
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {
        if(username==null || courseId==null) throw new IllegalArgumentException();
        Student s=studentRepository.findByUsername(username);
        Course c=courseRepository.findById(courseId);
        return courseRepository.addStudentToCourse(s,c);
    }
    @Override
    public List<Course> listAll () {
        return courseRepository.findAllCourses();
    }

    @Override
    public Course searchById(Long courseId) {
        return courseRepository.findById(courseId);
    }

    @Override
    public Course save(String name, String description, Long id) {
        Teacher teacher=teacherRepository.findById(id);
        return courseRepository.addCourse(name,description,teacher);
    }

    @Override
    public void delete(Long id) {
        courseRepository.removeCourse(id);
    }
}
