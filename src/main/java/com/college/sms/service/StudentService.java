package com.college.sms.service;

import com.college.sms.model.Student;
import com.college.sms.repository.StudentRepository;
import java.util.Collections;
import java.util.List;

public class StudentService {
    private final StudentRepository repo;

    public StudentService() {
        this.repo = new StudentRepository();
    }

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public void addStudent(Student s) { repo.save(s); }

    public void updateStudent(Student s) { repo.update(s); }

    public void deleteStudent(int id) { repo.deleteById(id); }

    public List<Student> getAllStudents() {
        try {
            return repo.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
