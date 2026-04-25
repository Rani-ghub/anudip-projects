package com.college.sms;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

import com.college.sms.model.Course;
import com.college.sms.service.CourseService;
import com.college.sms.service.StudentService;
import com.college.sms.service.EnrollmentService;

@WebServlet({"/addCourse", "/updateCourse", "/deleteCourse"})
public class CourseServlet extends HttpServlet {
    private final CourseService courseService = new CourseService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String path = req.getServletPath();

        if ("/addCourse".equals(path)) {
            String name = req.getParameter("courseName");
            String description = req.getParameter("description");
            String creditsStr = req.getParameter("credits");
            int credits = 0;
            try {
                if (creditsStr != null && !creditsStr.isBlank()) {
                    credits = Integer.parseInt(creditsStr);
                }
            } catch (NumberFormatException ignored) {}
            courseService.addCourse(new Course(name, description, credits));

        } else if ("/updateCourse".equals(path)) {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("courseName");
            String description = req.getParameter("description");
            int credits = Integer.parseInt(req.getParameter("credits"));
            courseService.updateCourse(new Course(id, name, description, credits));

        } else if ("/deleteCourse".equals(path)) {
            int id = Integer.parseInt(req.getParameter("id"));
            courseService.deleteCourse(id);
        }

        preloadLists(req);
        RequestDispatcher dispatcher = req.getRequestDispatcher("main.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        preloadLists(req);
        RequestDispatcher dispatcher = req.getRequestDispatcher("main.jsp");
        dispatcher.forward(req, resp);
    }

    private void preloadLists(HttpServletRequest req) {
        req.setAttribute("students", new StudentService().getAllStudents());
        req.setAttribute("courses", courseService.getAllCourses());
        req.setAttribute("enrollments", new EnrollmentService().getAllEnrollments());
    }
}
