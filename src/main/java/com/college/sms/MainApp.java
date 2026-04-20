package com.college.sms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.college.sms.repository.DatabaseInitializer;

import java.io.InputStream;
import java.util.Properties;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Load application.properties
        Properties props = new Properties();
        try (InputStream in = getClass().getResourceAsStream("/application.properties")) {
            props.load(in);
        }

        String appTitle = props.getProperty("app.title", "Student Management System");

        // Initialize DB schema from schema.sql
        DatabaseInitializer.init();

        // Load JavaFX UI
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/MainView.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle(appTitle);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
