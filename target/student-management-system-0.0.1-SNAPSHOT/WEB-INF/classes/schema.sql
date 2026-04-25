SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS studentdb.enrollments;
DROP TABLE IF EXISTS studentdb.courses;
DROP TABLE IF EXISTS studentdb.students;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE studentdb.students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE studentdb.courses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    credits INT DEFAULT 0
);

CREATE TABLE studentdb.enrollments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    enrollment_date DATE NOT NULL,
    FOREIGN KEY (student_id) REFERENCES studentdb.students(id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES studentdb.courses(id) ON DELETE CASCADE
);

INSERT INTO studentdb.students (name, email) VALUES
('Alice', 'alice@example.com'),
('Bob', 'bob@example.com');

INSERT INTO studentdb.courses (name, description, credits) VALUES
('Database Systems', 'Intro to relational databases', 4),
('Operating Systems', 'Processes, threads, scheduling', 3);

INSERT INTO studentdb.enrollments (student_id, course_id, enrollment_date) VALUES
(1, 1, CURDATE()),
(2, 2, CURDATE());
