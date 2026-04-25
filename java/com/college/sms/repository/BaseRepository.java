package com.college.sms.repository;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public abstract class BaseRepository {

    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    static {
        Properties props = new Properties();
        try (InputStream input = BaseRepository.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new RuntimeException("application.properties not found in resources");
            }
            props.load(input);

            url = props.getProperty("db.url");
            user = props.getProperty("db.username");
            password = props.getProperty("db.password");
            driver = props.getProperty("db.driver");

            // Load JDBC driver
            Class.forName(driver);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize database connection settings", e);
        }
    }

    protected static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to database", e);
        }
    }
}
