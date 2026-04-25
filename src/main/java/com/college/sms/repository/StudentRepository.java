package com.college.sms.repository;

import com.college.sms.model.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository extends BaseRepository {
    public void save(Student student) {
        String sql = "INSERT INTO students (name, email) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getEmail());
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Failed to save student", e);
        }
    }

    public void update(Student student) {
        String sql = "UPDATE students SET name=?, email=? WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getEmail());
            pstmt.setInt(3, student.getId());
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Failed to update student", e);
        }
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM students WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete student", e);
        }
    }

    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT id, name, email FROM students";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setEmail(rs.getString("email"));
                students.add(s);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch students", e);
        }
        return students;
    }
}
