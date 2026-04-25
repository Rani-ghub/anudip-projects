package com.college.sms;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

import com.college.sms.model.Student;
import com.college.sms.service.StudentService;
import com.college.sms.service.CourseService;
import com.college.sms.service.EnrollmentService;

@WebServlet({"/addStudent", "/updateStudent", "/deleteStudent"})
public class StudentServlet extends HttpServlet {
    private final StudentService studentService = new StudentService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String path = req.getServletPath();

        if ("/addStudent".equals(path)) {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            studentService.addStudent(new Student(name, email));

        } else if ("/updateStudent".equals(path)) {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            studentService.updateStudent(new Student(id, name, email));

        } else if ("/deleteStudent".equals(path)) {
            int id = Integer.parseInt(req.getParameter("id"));
            studentService.deleteStudent(id);
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
        req.setAttribute("students", studentService.getAllStudents());
        req.setAttribute("courses", new CourseService().getAllCourses());
        req.setAttribute("enrollments", new EnrollmentService().getAllEnrollments());
    }
}
