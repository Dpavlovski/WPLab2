package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;
    private final TeacherService teacherService;

    public CourseController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    @GetMapping
    public String getCoursesPage(@RequestParam(required = false) String error, Model model){
       if(error!=null && !error.isEmpty()) {
           model.addAttribute("hasError", true);
           model.addAttribute("message",error);
       }
        model.addAttribute("courses",courseService.listAll());
        return "listCourses";
    }

    @PostMapping("/add")
    public String saveCourse(@RequestParam String name,@RequestParam String desc,@RequestParam Long id){
        Course course=courseService.save(name,desc,id);
        if(course!=null)
            return "redirect:/courses";
        return "redirect:/courses/add-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id){
        courseService.delete(id);
        return "redirect:/courses";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditCoursePage(@PathVariable Long id,Model model) {
        if(courseService.searchById(id)==null)
            return "redirect:/courses?error=NoSuchElement";
        model.addAttribute("Course",courseService.searchById(id));
        model.addAttribute("teachers",teacherService.findAll());
        return "add-course";
    }

    @GetMapping("/add-form")
    public String getAddCoursePage(Model model) {
        model.addAttribute("teachers",teacherService.findAll());
        return "add-course";
    }

}