package com.college.sms.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.college.sms.model.Student;
import com.college.sms.service.StudentService;

public class StudentController {
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student, Integer> idCol;
    @FXML private TableColumn<Student, String> nameCol;
    @FXML private TableColumn<Student, String> emailCol;

    private final StudentService service = new StudentService();

    @FXML
    private void initialize() {
        idCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        nameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        emailCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEmail()));
        refreshTable();
    }

    @FXML
    private void handleSave() {
        Student s = new Student(nameField.getText(), emailField.getText());
        service.addStudent(s);
        refreshTable();
    }

    private void refreshTable() {
        studentTable.setItems(FXCollections.observableArrayList(service.getAllStudents()));
    }
}
