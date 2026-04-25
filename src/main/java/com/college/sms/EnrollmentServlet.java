package com.college.sms;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;

import com.college.sms.model.Enrollment;
import com.college.sms.service.EnrollmentService;
import com.college.sms.service.StudentService;
import com.college.sms.service.CourseService;

@WebServlet("/addEnrollment")
public class EnrollmentServlet extends HttpServlet {
    private final EnrollmentService enrollmentService = new EnrollmentService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int studentId = Integer.parseInt(req.getParameter("studentId")); // use ID for enrollment
        int courseId = Integer.parseInt(req.getParameter("courseId"));

        Enrollment enrollment = new Enrollment();
        enrollment.setStudentId(studentId);
        enrollment.setCourseId(courseId);
        enrollment.setEnrollmentDate(LocalDate.now());
        enrollmentService.addEnrollment(enrollment);

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
        req.setAttribute("courses", new CourseService().getAllCourses());
        req.setAttribute("enrollments", enrollmentService.getAllEnrollments());
    }
}
