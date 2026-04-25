package com.college.sms.service;

import com.college.sms.model.Enrollment;
import com.college.sms.repository.BaseRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentService extends BaseRepository {

    public void addEnrollment(Enrollment e) {
        String sql = "INSERT INTO enrollments (student_id, course_id, enrollment_date) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, e.getStudentId());
            ps.setInt(2, e.getCourseId());
            ps.setDate(3, Date.valueOf(
                e.getEnrollmentDate() != null ? e.getEnrollmentDate() : LocalDate.now()
            ));
            ps.executeUpdate();
        } catch (Exception ex) {
            throw new RuntimeException("Error adding enrollment", ex);
        }
    }

    public List<Enrollment> getAllEnrollments() {
        List<Enrollment> list = new ArrayList<>();
        String sql = "SELECT e.id, e.student_id, e.course_id, e.enrollment_date, " +
                     "s.name AS studentName, c.name AS courseName " +
                     "FROM enrollments e " +
                     "JOIN students s ON e.student_id = s.id " +
                     "JOIN courses c ON e.course_id = c.id";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Enrollment e = new Enrollment();
                e.setId(rs.getInt("id"));
                e.setStudentId(rs.getInt("student_id"));
                e.setCourseId(rs.getInt("course_id"));
                e.setEnrollmentDate(rs.getDate("enrollment_date").toLocalDate());
                e.setStudentName(rs.getString("studentName"));
                e.setCourseName(rs.getString("courseName"));
                list.add(e);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error fetching enrollments", ex);
        }
        return list;
    }
}
