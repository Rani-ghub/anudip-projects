package com.college.sms;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.io.InputStream;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

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
            String sql = "SELECT password FROM users WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();

                if (rs.next() && rs.getString("password").equals(password)) {
                    HttpSession session = req.getSession();
                    session.setAttribute("user", username);
                    resp.sendRedirect("main.jsp");
                } else {
                    req.setAttribute("error", "Invalid username or password");
                    RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
                    dispatcher.forward(req, resp);
                }
            }
        } catch (Exception e) {
            throw new ServletException("Login failed", e);
        }
    }
}
