<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Management System</title>
</head>
<body>
    <h1>Welcome to Student Management System</h1>
    <p>This is the web interface running on Jetty/Tomcat.</p>

    <h2>Add Student</h2>
    <form action="addStudent" method="post">
        Name: <input type="text" name="name" /><br/>
        Email: <input type="text" name="email" /><br/>
        <input type="submit" value="Add Student" />
    </form>
</body>
</html>
