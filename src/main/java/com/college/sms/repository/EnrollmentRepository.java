package com.college.sms.repository;

import com.college.sms.model.Enrollment;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class EnrollmentRepository extends BaseRepository {

    public void save(Enrollment enrollment) {
        String sql = "INSERT INTO enrollments (student_id, course_id, enrollment_date) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, enrollment.getStudentId());
            pstmt.setInt(2, enrollment.getCourseId());
            pstmt.setDate(3, Date.valueOf(enrollment.getEnrollmentDate()));
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Failed to save enrollment", e);
        }
    }

    public List<Enrollment> findAll() {
        List<Enrollment> enrollments = new ArrayList<>();
        String sql = "SELECT id, student_id, course_id, enrollment_date FROM enrollments";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Enrollment e = new Enrollment();
                e.setId(rs.getInt("id"));
                e.setStudentId(rs.getInt("student_id"));
                e.setCourseId(rs.getInt("course_id"));
                e.setEnrollmentDate(rs.getDate("enrollment_date").toLocalDate());
                enrollments.add(e);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch enrollments", e);
        }
        return enrollments;
    }
}
