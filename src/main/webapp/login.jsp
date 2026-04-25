<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login - Student Management System</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <style>
        body {
            background: linear-gradient(135deg, #6a11cb 0%, #2575fc 100%);
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .card {
            border-radius: 15px;
            overflow: hidden;
            width: 380px;
        }
        .card-header {
            background-color: #2575fc;
            color: white;
            text-align: center;
            padding: 20px;
        }
        .card-header img {
            width: 60px;
            margin-bottom: 10px;
        }
        .form-control {
            padding: 12px;
            border-radius: 8px;
        }
        .btn-primary {
            background-color: #2575fc;
            border: none;
            border-radius: 8px;
            padding: 10px;
            font-weight: bold;
        }
        .btn-primary:hover {
            background-color: #1a5edb;
        }
    </style>
</head>
<body>

<div class="card shadow-lg">
    <div class="card-header">
        <img src="images/student-icon.png" alt="Student Icon">
        <h3 class="mt-2">Student Management System</h3>
        <p class="mb-0">Login to continue</p>
    </div>
    <div class="card-body p-4">
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <form action="login" method="post">
            <div class="mb-3">
                <label class="form-label">Username</label>
                <input type="text" name="username" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Password</label>
                <input type="password" name="password" class="form-control" required>
            </div>
            <button type="submit" class="btn btn-primary w-100">Login</button>
        </form>

        <div class="text-center mt-3">
            <a href="register.jsp" class="text-decoration-none">Don't have an account? Register</a>
        </div>
    </div>
</div>

</body>
</html>
