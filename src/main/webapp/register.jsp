<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register - Student Management System</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <style>
        body {
            background: linear-gradient(135deg, #ff512f 0%, #dd2476 100%);
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .card {
            border-radius: 15px;
            overflow: hidden;
            width: 420px;
        }
        .card-header {
            background-color: #dd2476;
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
        .btn-success {
            background-color: #dd2476;
            border: none;
            border-radius: 8px;
            padding: 10px;
            font-weight: bold;
        }
        .btn-success:hover {
            background-color: #c01f65;
        }
    </style>
</head>
<body>

<div class="card shadow-lg">
    <div class="card-header">
        <img src="images/register-icon.png" alt="Register Icon">
        <h3 class="mt-2">Student Management System</h3>
        <p class="mb-0">Create your account</p>
    </div>
    <div class="card-body p-4">
        <c:if test="${not empty success}">
            <div class="alert alert-success">${success}</div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <form action="register" method="post">
            <div class="mb-3">
                <label class="form-label">Username</label>
                <input type="text" name="username" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Password</label>
                <input type="password" name="password" class="form-control" required>
            </div>
            <button type="submit" class="btn btn-success w-100">Register</button>
        </form>

        <div class="text-center mt-3">
            <a href="login.jsp" class="text-decoration-none">Already have an account? Login</a>
        </div>
    </div>
</div>

</body>
</html>
