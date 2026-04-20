# 🎓 Student Management System

This project is a **JavaFX + MySQL application** that manages students, courses, and enrollments.  
It demonstrates how to integrate a relational database with a JavaFX front‑end, using Maven for build and execution.

---

## 📂 Project Structure

student-management-system/
│
├── pom.xml                  # Maven build configuration
├── README.md                # Project documentation (this file)
├── schema.sql               # SQL script to reset and populate the database
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/studentms/
│   │   │       ├── MainApp.java        # JavaFX entry point
│   │   │       ├── DatabaseUtil.java   # Utility for DB connection
│   │   │       ├── Student.java        # Model class
│   │   │       ├── Course.java         # Model class
│   │   │       ├── Enrollment.java     # Model class
│   │   │       ├── dao/                # Data Access Objects
│   │   │       └── controllers/        # JavaFX controllers
│   │   └── resources/
│   │       ├── schema.sql              # Copy of DB schema for initialization
│   │       └── views/                  # FXML files for UI
│   └── test/
│       └── java/                       # Unit tests
│
└── docs/
└── ERD.png              # Entity Relationship Diagram (optional visual)

Code

---

## 🗄️ Database Schema

### Tables
- **students** → `id, name, email`
- **courses** → `id, name, description, credits`
- **enrollments** → `id, student_id, course_id, enrollment_date`

### Relationships
- One **student** can enroll in many **courses**.
- One **course** can have many **students**.
- The **enrollments** table is the junction table linking students ↔ courses.

---

## 🔄 SQL Reset Script (`schema.sql`)

The `schema.sql` file:
- Drops existing tables (`students`, `courses`, `enrollments`).
- Recreates them with the correct schema.
- Inserts sample data for testing.

Run it manually in MySQL Workbench or via CLI:
```bash
mysql -u root -p studentdb < schema.sql
🛠️ Prerequisites
Before running the project:

Install MySQL and ensure the server is running.

Create the schema:

sql
CREATE DATABASE studentdb;
Execute schema.sql to create tables and insert sample data.

Install Java 17+ and Maven.

Ensure environment variables (JAVA_HOME, MAVEN_HOME) are set.

▶️ Execution Flow
Step 1: Build the project
bash
mvn clean install -DskipTests
Cleans previous builds.

Compiles source code.

Skips unit tests for faster build.

![ER Diagram](docs/mvn-clean-test.png)

Step 2: Run the JavaFX application
bash
mvn javafx:run
Launches the JavaFX UI.

Connects to the studentdb schema in MySQL.

Allows interaction with students, courses, and enrollments.

1.Adding Students
![ER Diagram](docs/student-add.png)

2.Adding Cource
![ER Diagram](docs/cource-add.png)

3.Enrolling Cource
![ER Diagram](docs/enroll-cource.png)


📊 Example Queries (for manual DB testing)
List all students with their enrolled courses
sql
SELECT s.name AS student_name,
       c.name AS course_name,
       e.enrollment_date
FROM studentdb.enrollments e
JOIN studentdb.students s ON e.student_id = s.id
JOIN studentdb.courses c ON e.course_id = c.id;
Count how many students are enrolled in each course
sql
SELECT c.name AS course_name,
       COUNT(e.student_id) AS total_enrolled
FROM studentdb.courses c
LEFT JOIN studentdb.enrollments e ON c.id = e.course_id
GROUP BY c.name;
📈 Future Enhancements
Add more attributes (e.g., student age, course duration).

Add validation and error handling in the UI.

Extend DAO layer with pagination and search.

Integrate with REST API for remote access.

✅ License
This project is for educational purposes and is free to use, modify, and extend.

Code

---

### 🔎 Key Points
- Title corrected to **Student Management System**.  
- Includes **folder hierarchy** for a Maven + JavaFX project.  
- **Manual MySQL setup** steps clearly documented.  
- **Execution commands** (`mvn clean install -DskipTests`, `mvn javafx:run`) included in step‑by‑step flow.  
- Example SQL queries for testing relationships.  