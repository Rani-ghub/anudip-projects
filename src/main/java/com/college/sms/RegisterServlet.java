package com.college.sms;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.io.InputStream;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private String jdbcUrl;
    private String jdbcUser;
    private String jdbcPass;
    private String jdbcDriver;

    @Override
    public void init() throws ServletException {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            Properties props = new Properties();
            props.load(input);

            jdbcUrl = props.getProperty("db.url");
            jdbcUser = props.getProperty("db.username");
            jdbcPass = props.getProperty("db.password");
            jdbcDriver = props.getProperty("db.driver");

            Class.forName(jdbcDriver);
        } catch (Exception e) {
            throw new ServletException("Failed to load DB properties", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPass)) {
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, password); // ⚠️ In production, hash this!
                stmt.executeUpdate();

                req.setAttribute("success", "User registered successfully. Please login.");
                RequestDispatcher dispatcher = req.getRequestDispatcher("register.jsp");
                dispatcher.forward(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("error", "Registration failed: " + e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("register.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
