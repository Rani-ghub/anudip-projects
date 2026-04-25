package com.college.sms.service;

import com.college.sms.model.Student;
import com.college.sms.repository.StudentRepository;

import java.util.Collections;
import java.util.List;

public class StudentService {
    private final StudentRepository repo;

    public StudentService() {
        repo = new StudentRepository();
    }

    public void addStudent(Student s) {
        repo.save(s);
    }

    public List<Student> getAllStudents() {
        try {
            return repo.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
