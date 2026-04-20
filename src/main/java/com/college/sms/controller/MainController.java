package com.college.sms.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import com.college.sms.model.*;
import com.college.sms.service.*;

import java.time.LocalDate;

public class MainController {

    private final StudentService studentService = new StudentService();
    private final CourseService courseService = new CourseService();
    private final EnrollmentService enrollmentService = new EnrollmentService();

    @FXML private TextField studentNameField, studentEmailField;
    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student, Integer> studentIdCol;
    @FXML private TableColumn<Student, String> studentNameCol, studentEmailCol;

    @FXML private TextField courseNameField, courseDescField, courseCreditsField;
    @FXML private TableView<Course> courseTable;
    @FXML private TableColumn<Course, Integer> courseIdCol, courseCreditsCol;
    @FXML private TableColumn<Course, String> courseNameCol, courseDescCol;

    @FXML private ComboBox<Student> studentCombo;
    @FXML private ComboBox<Course> courseCombo;
    @FXML private TableView<Enrollment> enrollmentTable;
    @FXML private TableColumn<Enrollment, Integer> enrollIdCol;
    @FXML private TableColumn<Enrollment, String> enrollStudentCol, enrollCourseCol;
    @FXML private TableColumn<Enrollment, LocalDate> enrollDateCol;

    @FXML
    public void initialize() {
        // Bind Student columns
        studentIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        studentNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        studentEmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        studentTable.getItems().setAll(studentService.getAllStudents());
        studentCombo.getItems().setAll(studentService.getAllStudents());

        // Bind Course columns
        courseIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        courseNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        courseDescCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        courseCreditsCol.setCellValueFactory(new PropertyValueFactory<>("credits"));
        courseTable.getItems().setAll(courseService.getAllCourses());
        courseCombo.getItems().setAll(courseService.getAllCourses());

        // Bind Enrollment columns
        enrollIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        enrollStudentCol.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        enrollCourseCol.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        enrollDateCol.setCellValueFactory(new PropertyValueFactory<>("enrollmentDate"));
        enrollmentTable.getItems().setAll(enrollmentService.getAllEnrollments());
    }

    @FXML
    private void handleAddStudent() {
        Student s = new Student(studentNameField.getText(), studentEmailField.getText());
        studentService.addStudent(s);
        studentTable.getItems().setAll(studentService.getAllStudents());
        studentCombo.getItems().setAll(studentService.getAllStudents());
    }

    @FXML
    private void handleAddCourse() {
        Course c = new Course(courseNameField.getText(), courseDescField.getText(),
                              Integer.parseInt(courseCreditsField.getText()));
        courseService.addCourse(c);
        courseTable.getItems().setAll(courseService.getAllCourses());
        courseCombo.getItems().setAll(courseService.getAllCourses());
    }

    @FXML
    private void handleEnroll() {
        Student s = studentCombo.getValue();
        Course c = courseCombo.getValue();
        if (s != null && c != null) {
            Enrollment e = new Enrollment();
            e.setStudentId(s.getId());
            e.setCourseId(c.getId());
            e.setEnrollmentDate(LocalDate.now());
            enrollmentService.addEnrollment(e);
            enrollmentTable.getItems().setAll(enrollmentService.getAllEnrollments());
        }
    }
}
