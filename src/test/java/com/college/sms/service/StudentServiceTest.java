package com.college.sms.service;

import com.college.sms.model.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {

    private static StudentService service;

    @BeforeAll
    static void setup() {
        service = new StudentService();
    }

    @Test
    void testAddStudent() {
        Student s = new Student();
        s.setName("Alice");
        s.setEmail("alice@example.com");

        service.addStudent(s);

        List<Student> students = service.getAllStudents();
        assertTrue(students.stream().anyMatch(st -> "Alice".equals(st.getName())));
    }

    @Test
    void testGetAllStudents() {
        List<Student> students = service.getAllStudents();
        assertNotNull(students);
    }
}
