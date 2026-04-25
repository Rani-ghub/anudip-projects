package com.college.sms.service;

import com.college.sms.model.Course;
import com.college.sms.repository.CourseRepository;
import java.util.List;

public class CourseService {
    private final CourseRepository repo = new CourseRepository();

    public void addCourse(Course c) { repo.save(c); }
    public List<Course> getAllCourses() { return repo.findAll(); }
}
