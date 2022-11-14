package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet(name = "NewStudentsServlet", urlPatterns = "/NewStudentServlet")
public class NewStudentsServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final CourseService courseService;

    public NewStudentsServlet(SpringTemplateEngine springTemplateEngine, CourseService courseService) {
        this.springTemplateEngine = springTemplateEngine;
        this.courseService = courseService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        WebContext context=new WebContext(req,resp,req.getServletContext());
        long courseId=Long.parseLong(req.getSession().getAttribute("courseId").toString());
        String courseName=courseService.searchById(courseId).getName();
        context.setVariable("course",courseName);
        context.setVariable("students",courseService.listStudentsByCourse(courseId).stream().filter(Student::isNew).toList());
        springTemplateEngine.process("newStudents.html",context,resp.getWriter());
    }

}
