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

```
---
### 📦 **Purpose of Each Component**
Servlets (com.college.sms) → Handle HTTP requests and responses.

Models (com.college.model) → Represent entities (Student, Course, Enrollment).

Repositories (com.college.repository) → Database access layer (DAO classes).

Services (com.college.service) → Business logic layer.

Resources → Store configuration and schema.

Webapp (JSPs) → Provide UI.

Images → Enhance UI with icons and backgrounds.

WEB-INF → Deployment configuration.

### ⚙️ Setup Instructions
# 1. Database
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
# 2. Configuration
Edit src/main/resources/application.properties:

properties
db.url=jdbc:mysql://localhost:3306/studentdb
db.username=root
db.password=yourpassword
db.driver=com.mysql.cj.jdbc.Driver

# 3. Dependencies
Ensure your pom.xml includes:

mysql-connector-java

jetty-maven-plugin

javax.servlet-api


### ▶️ Running the Project (Step by Step)
# 1.Clone the repository

bash
git clone https://github.com/yourusername/StudentManagementSystem.git
cd StudentManagementSystem

# 2.Configure database

Update application.properties with your DB credentials.

Run schema.sql to initialize tables.

# 3.Build the project

bash
mvn clean install

# 4.Run with Jetty

bash
mvn jetty:run

# 5.Access in browser

Login: http://localhost:9090/login.jsp 

Register: http://localhost:9090/register.jsp 

Dashboard: http://localhost:9090/main.jsp 


### 🎨 UI Functionality (End‑to‑End Workflows)
# 1. User Authentication
Login (login.jsp)

User enters username and password.

Credentials validated via LoginServlet → UserService.

On success, redirected to main.jsp dashboard.

Register (register.jsp)

User enters new account details (username, email, password).

Data stored in users table via RegisterServlet.

Redirected to login page after successful registration.

Logout

Ends session via LogoutServlet.

Redirects back to login page.

# 2. Student Management
Add Student

Form in Students tab (main.jsp) with fields: name, email.

Submitted to StudentServlet → StudentService → StudentRepository.

New student record inserted into DB.

Search Students

Table lists all students.

Search/filter functionality to quickly locate a student.

Edit Student

Select a student record → edit form.

Updates persisted via StudentServlet.

Delete Student

Delete button removes student record from DB.

# 3. Course Management
Add Course

Form in Courses tab with fields: course name, description.

Submitted to CourseServlet → CourseService → CourseRepository.

Search Courses

Table lists all courses with search/filter.

Edit Course

Update course details via edit form.

Delete Course

Remove course record from DB.

# 4. Enrollment Management
Enroll Student in Course

Form in Enrollments tab: select student + select course.

Submitted to EnrollmentServlet → EnrollmentService → EnrollmentRepository.

View Enrollments
Table lists all student‑course enrollments.

Search Enrollments

Table lists all courses with search/filter.

## ✅ **User Journey**
# 1. **Register** a new account.

   
   <img width="952" height="478" alt="register jpeg" src="https://github.com/user-attachments/assets/ba629e0a-14a0-446c-ada3-b6a8a4604a67" />


# 3. **Login** with credentials.  


<img width="952" height="476" alt="loginnew jpeg" src="https://github.com/user-attachments/assets/13bc6b1d-cdd3-4b52-b4e3-dc05faa039c7" />


# 4. Navigate to **Dashboard** (`main.jsp`).  

<img width="959" height="475" alt="dashboard jpeg" src="https://github.com/user-attachments/assets/b69e94ae-038e-4a4d-a1fa-aa7eb5462e98" />


# 5. Use tabs to **manage Students, Courses, and Enrollments** (Add, Search, Edit, Delete).  
   # 1.Students

   <img width="954" height="473" alt="image" src="https://github.com/user-attachments/assets/f87089f4-09e9-4167-a206-90b4f6ac3f30" />

   # 2.Courses

   <img width="959" height="476" alt="image" src="https://github.com/user-attachments/assets/1dd15ab8-970a-4b9b-9fbc-6ba284f1aac5" />

  # 3.Enrollments

  <img width="959" height="470" alt="image" src="https://github.com/user-attachments/assets/429ad121-f690-46ab-87a5-bd91e10251f3" />


# 6. **Logout** when finished.


<img width="953" height="92" alt="image" src="https://github.com/user-attachments/assets/e3c96d38-aeb8-4da7-9512-64fd9fdd6939" />


