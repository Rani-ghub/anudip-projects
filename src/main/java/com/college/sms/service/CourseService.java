package com.college.sms.service;

import com.college.sms.model.Course;
import com.college.sms.repository.CourseRepository;
import java.util.Collections;
import java.util.List;

public class CourseService {
    private final CourseRepository repo;

    public CourseService() {
        this.repo = new CourseRepository();
    }

    public CourseService(CourseRepository repo) {
        this.repo = repo;
    }

    public void addCourse(Course c) { repo.save(c); }

    public void updateCourse(Course c) { repo.update(c); }

    public void deleteCourse(int id) { repo.deleteById(id); }

    public List<Course> getAllCourses() {
        try {
            return repo.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
