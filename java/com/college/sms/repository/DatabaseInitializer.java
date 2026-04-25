package com.college.sms.repository;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer extends BaseRepository {

    public static void init() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            // Drop tables in correct order (child first)
            stmt.executeUpdate("DROP TABLE IF EXISTS enrollments");
            stmt.executeUpdate("DROP TABLE IF EXISTS courses");
            stmt.executeUpdate("DROP TABLE IF EXISTS students");

            // Create Students table
            stmt.executeUpdate(
                "CREATE TABLE students (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(100) NOT NULL," +
                "email VARCHAR(100) UNIQUE NOT NULL)"
            );

            // Create Courses table
            stmt.executeUpdate(
                "CREATE TABLE courses (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(100) NOT NULL," +
                "description VARCHAR(255)," +
                "credits INT DEFAULT 0)"
            );

            // Create Enrollments table
            stmt.executeUpdate(
                "CREATE TABLE enrollments (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "student_id INT NOT NULL," +
                "course_id INT NOT NULL," +
                "enrollment_date DATE NOT NULL," +
                "FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE," +
                "FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE)"
            );

            // Insert sample students
            stmt.executeUpdate(
                "INSERT INTO students (name, email) VALUES " +
                "('Alice', 'alice@example.com')," +
                "('Bob', 'bob@example.com')"
            );

            // Insert sample courses
            stmt.executeUpdate(
                "INSERT INTO courses (name, description, credits) VALUES " +
                "('Database Systems', 'Intro to relational databases', 4)," +
                "('Operating Systems', 'Processes, threads, scheduling', 3)"
            );

            // Insert sample enrollments
            stmt.executeUpdate(
                "INSERT INTO enrollments (student_id, course_id, enrollment_date) VALUES " +
                "(1, 1, CURDATE())," +
                "(2, 2, CURDATE())"
            );

            System.out.println("Database schema initialized successfully.");

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize database schema", e);
        }
    }
}
