# 🎓 Student Management System

A Java Servlet + JSP + JDBC based Student Management System with MySQL backend.  
This project demonstrates user authentication, student/course/enrollment management, and a styled dashboard with modern UI enhancements.

---

## 📂 Project Hierarchy

```text
StudentManagementSystem/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── college/
│       │           ├── sms/                  # Servlets (controllers)
│       │           │   ├── LoginServlet.java
│       │           │   ├── LogoutServlet.java
│       │           │   ├── RegisterServlet.java
│       │           │   ├── StudentServlet.java
│       │           │   ├── CourseServlet.java
│       │           │   └── EnrollmentServlet.java
│       │           │
│       │           ├── model/                # Entity classes
│       │           │   ├── Student.java
│       │           │   ├── Course.java
│       │           │   └── Enrollment.java
│       │           │
│       │           ├── repository/           # Database layer
│       │           │   ├── BaseRepository.java
│       │           │   ├── DatabaseInitializer.java
│       │           │   ├── StudentRepository.java
│       │           │   ├── CourseRepository.java
│       │           │   └── EnrollmentRepository.java
│       │           │
│       │           └── service/              # Business logic
│       │               ├── UserService.java
│       │               ├── StudentService.java
│       │               ├── CourseService.java
│       │               └── EnrollmentService.java
│       │
│       ├── resources/
│       │   ├── application.properties        # DB configuration
│       │   └── schema.sql                    # SQL schema
│       │
│       └── webapp/
│           ├── index.jsp
│           ├── login.jsp
│           ├── register.jsp
│           ├── main.jsp
│           │
│           ├── images/
│           │   ├── student-icon.png
│           │   ├── register-icon.png
│           │   └── dashboard-bg.jpg
│           │
│           └── WEB-INF/
│               └── web.xml                   # Deployment descriptor
├── pom.xml                                   # Maven build configuration
├── README.md                                 # Project documentation (this file)

📦 Purpose of Each Component
Servlets (com.college.sms) → Handle HTTP requests and responses.

Models (com.college.model) → Represent entities (Student, Course, Enrollment).

Repositories (com.college.repository) → Database access layer (DAO classes).

Services (com.college.service) → Business logic layer.

Resources → Store configuration and schema.

Webapp (JSPs) → Provide UI.

Images → Enhance UI with icons and backgrounds.

WEB-INF → Deployment configuration.

⚙️ Setup Instructions
1. Database
Create a MySQL database named studentdb and tables using schema.sql.

sql
CREATE DATABASE studentdb;
USE studentdb;

-- Example users table
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL
);
2. Configuration
Edit src/main/resources/application.properties:

properties
db.url=jdbc:mysql://localhost:3306/studentdb
db.username=root
db.password=yourpassword
db.driver=com.mysql.cj.jdbc.Driver

3. Dependencies
Ensure your pom.xml includes:

mysql-connector-java

jetty-maven-plugin

javax.servlet-api


▶️ Running the Project (Step by Step)
1.Clone the repository

bash
git clone https://github.com/yourusername/StudentManagementSystem.git
cd StudentManagementSystem

2.Configure database

Update application.properties with your DB credentials.

Run schema.sql to initialize tables.

3.Build the project

bash
mvn clean install

4.Run with Jetty

bash
mvn jetty:run

5.Access in browser

Login: http://localhost:9090/login.jsp 

Register: http://localhost:9090/register.jsp 

Dashboard: http://localhost:9090/main.jsp 


🎨 UI Features
Login & Register Pages → Gradient backgrounds, icons, clear headers.

Dashboard (main.jsp) → Blinking background image with overlay for readability.

Bootstrap 5 → Responsive design and modern components.

