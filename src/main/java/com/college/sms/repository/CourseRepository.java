package com.college.sms.repository;

import com.college.sms.model.Course;
import java.sql.*;
import java.util.*;

public class CourseRepository extends BaseRepository {

    public void save(Course course) {
        String sql = "INSERT INTO courses (name, description, credits) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, course.getName());
            pstmt.setString(2, course.getDescription());
            pstmt.setInt(3, course.getCredits() > 0 ? course.getCredits() : 0);
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Failed to save course", e);
        }
    }

    public void update(Course course) {
        String sql = "UPDATE courses SET name=?, description=?, credits=? WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, course.getName());
            pstmt.setString(2, course.getDescription());
            pstmt.setInt(3, course.getCredits());
            pstmt.setInt(4, course.getId());
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Failed to update course", e);
        }
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM courses WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete course", e);
        }
    }

    public List<Course> findAll() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT id, name, description, credits FROM courses";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Course c = new Course();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setDescription(rs.getString("description"));
                c.setCredits(rs.getInt("credits"));
                courses.add(c);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch courses", e);
        }
        return courses;
    }
}
