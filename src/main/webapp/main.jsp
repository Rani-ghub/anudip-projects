
<%
    String user = (String) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<div
	class="d-flex justify-content-between align-items-center mb-4 p-3 bg-primary text-white rounded shadow-sm">
	<h2 class="mb-0">Student Management System</h2>
	<div>
		<span class="me-3">Logged in as: <strong><%= user %></strong></span> <a
			href="logout" class="btn btn-light btn-sm">Logout</a>
	</div>
</div>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Student Management System</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<style>
body {
	margin: 0;
	padding: 0;
	min-height: 100vh;
	background: url("images/dashboard-bg.jpg") no-repeat center center fixed;
	background-size: cover;
	animation: blinkBackground 6s infinite;
}

@
keyframes blinkBackground { 0% {
	filter: brightness(100%);
}

50
%
{
filter
:
brightness(
70%
);
}
100
%
{
filter
:
brightness(
100%
);
}
}
h1 {
	color: #0d6efd;
}

.nav-tabs .nav-link.active {
	background-color: #0d6efd;
	color: #fff;
}

.table thead {
	background-color: #0d6efd;
	color: #fff;
}

.btn-primary {
	background-color: #0d6efd;
}

.form-label {
	font-weight: bold;
}

/* Optional overlay to keep text readable */
.overlay {
	background-color: rgba(0, 0, 0, 0.4);
	min-height: 100vh;
	padding: 20px;
}
</style>
</head>

<body>
    <div class="overlay container mt-4">
	<h1 class="mb-4 text-white">Student Management System</h1>

	<!-- Navigation Tabs -->
	<ul class="nav nav-tabs" id="myTab" role="tablist">
		<li class="nav-item"><button class="nav-link active"
				data-bs-toggle="tab" data-bs-target="#students">Students</button></li>
		<li class="nav-item"><button class="nav-link"
				data-bs-toggle="tab" data-bs-target="#courses">Courses</button></li>
		<li class="nav-item"><button class="nav-link"
				data-bs-toggle="tab" data-bs-target="#enrollments">Enrollments</button></li>
	</ul>

	<!-- Tab Content -->
	<div class="tab-content mt-3">
		<!-- Students Tab -->
		<div class="tab-pane fade show active" id="students" role="tabpanel">
			<h3>Add Student</h3>
			<form action="addStudent" method="post" class="mb-3 row">
				<div class="col-md-4">
					<label class="form-label">Name</label> <input type="text"
						name="name" required class="form-control">
				</div>
				<div class="col-md-4">
					<label class="form-label">Email</label> <input type="email"
						name="email" required class="form-control">
				</div>
				<div class="col-md-4 d-flex align-items-end">
					<button type="submit" class="btn btn-primary w-100">Add
						Student</button>
				</div>
			</form>

			<h3>Search Students</h3>
			<input type="text" id="studentSearch" placeholder="Search..."
				class="form-control mb-2">

			<h3>Student List</h3>
			<table class="table table-striped" id="studentTable">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Email</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="s" items="${students}">
						<tr>
							<td>${s.id}</td>
							<td>${s.name}</td>
							<td>${s.email}</td>
							<td>
								<button type="button" class="btn btn-sm btn-warning"
									data-bs-toggle="modal" data-bs-target="#updateStudentModal"
									onclick="fillStudentModal(${s.id}, '${s.name}', '${s.email}')">
									Update</button>
								<form action="deleteStudent" method="post"
									style="display: inline;">
									<input type="hidden" name="id" value="${s.id}">
									<button type="submit" class="btn btn-sm btn-danger"
										onclick="return confirm('Are you sure you want to delete this student?');">
										Delete</button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<!-- Courses Tab -->
		<div class="tab-pane fade" id="courses" role="tabpanel">
			<h3>Add Course</h3>
			<form action="addCourse" method="post" class="mb-3 row">
				<div class="col-md-3">
					<label class="form-label">Course Name</label> <input type="text"
						name="courseName" required class="form-control">
				</div>
				<div class="col-md-4">
					<label class="form-label">Description</label> <input type="text"
						name="description" class="form-control">
				</div>
				<div class="col-md-2">
					<label class="form-label">Credits</label> <input type="number"
						name="credits" class="form-control">
				</div>
				<div class="col-md-3 d-flex align-items-end">
					<button type="submit" class="btn btn-primary w-100">Add
						Course</button>
				</div>
			</form>
			<h3>Search Courses</h3>
			<input type="text" id="courseSearch" placeholder="Search..."
				class="form-control mb-2">
			<h3>Course List</h3>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Description</th>
						<th>Credits</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="c" items="${courses}">
						<tr>
							<td>${c.id}</td>
							<td>${c.name}</td>
							<td>${c.description}</td>
							<td>${c.credits}</td>
							<td>
								<button type="button" class="btn btn-sm btn-warning"
									data-bs-toggle="modal" data-bs-target="#updateCourseModal"
									onclick="fillCourseModal(${c.id}, '${c.name}', '${c.description}', ${c.credits})">
									Update</button>
								<form action="deleteCourse" method="post"
									style="display: inline;">
									<input type="hidden" name="id" value="${c.id}">
									<button type="submit" class="btn btn-sm btn-danger"
										onclick="return confirm('Are you sure you want to delete this course?');">
										Delete</button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<!-- Enrollments Tab -->
		<div class="tab-pane fade" id="enrollments" role="tabpanel">
			<h3>Manage Enrollments</h3>
			<form action="addEnrollment" method="post" class="mb-3 row">
				<!-- Student Name Dropdown -->
				<div class="col-md-4">
					<label class="form-label">Select Student (Name)</label> <select
						name="studentName" class="form-control">
						<c:forEach var="s" items="${students}">
							<option value="${s.id}">${s.name}</option>
						</c:forEach>
					</select>
				</div>

				<!-- Student ID Dropdown -->
				<div class="col-md-4">
					<label class="form-label">Select Student ID</label> <select
						name="studentId" class="form-control">
						<c:forEach var="s" items="${students}">
							<option value="${s.id}">${s.id}</option>
						</c:forEach>
					</select>
				</div>

				<!-- Course Dropdown -->
				<div class="col-md-4">
					<label class="form-label">Select Course</label> <select
						name="courseId" class="form-control">
						<c:forEach var="c" items="${courses}">
							<option value="${c.id}">${c.name}(${c.credits}credits)</option>
						</c:forEach>
					</select>
				</div>

				<div class="col-md-12 d-flex align-items-end mt-2">
					<button type="submit" class="btn btn-primary w-100">Enroll</button>
				</div>
			</form>
			<h3>Search Enrollments</h3>
			<input type="text" id="enrollmentSearch" placeholder="Search..."
				class="form-control mb-2">
			<h3>Enrollment List</h3>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>ID</th>
						<th>Student</th>
						<th>Course</th>
						<th>Date</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="e" items="${enrollments}">
						<tr>
							<td>${e.id}</td>
							<td>${e.studentName}</td>
							<td>${e.courseName}</td>
							<td>${e.enrollmentDate}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

	</div>

	<!-- Update Student Modal -->
	<div class="modal fade" id="updateStudentModal" tabindex="-1">
		<div class="modal-dialog">
			<form action="updateStudent" method="post" class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Update Student</h5>
				</div>
				<div class="modal-body">
					<input type="hidden" name="id" id="updateStudentId">
					<div class="mb-2">
						<label class="form-label">Name</label> <input type="text"
							name="name" id="updateStudentName" class="form-control">
					</div>
					<div class="mb-2">
						<label class="form-label">Email</label> <input type="email"
							name="email" id="updateStudentEmail" class="form-control">
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary">Save Changes</button>
				</div>
			</form>
		</div>
	</div>

	<!-- Update Course Modal -->
	<div class="modal fade" id="updateCourseModal" tabindex="-1">
		<div class="modal-dialog">
			<form action="updateCourse" method="post" class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Update Course</h5>
				</div>
				<div class="modal-body">
					<input type="hidden" name="id" id="updateCourseId">
					<div class="mb-2">
						<label class="form-label">Course Name</label> <input type="text"
							name="courseName" id="updateCourseName" class="form-control">
					</div>
					<div class="mb-2">
						<label class="form-label">Description</label> <input type="text"
							name="description" id="updateCourseDescription"
							class="form-control">
					</div>
					<div class="mb-2">
						<label class="form-label">Credits</label> <input type="number"
							name="credits" id="updateCourseCredits" class="form-control">
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary">Save Changes</button>
				</div>
			</form>
		</div>
	</div>

	<script>
  // Search filter for students
  document.getElementById("studentSearch").addEventListener("keyup", function() {
      let filter = this.value.toLowerCase();
      let rows = document.querySelectorAll("#studentTable tbody tr");
      rows.forEach(row => {
          row.style.display = row.textContent.toLowerCase().includes(filter) ? "" : "none";
      });
  });

  // Search filter for courses
  document.getElementById("courseSearch").addEventListener("keyup", function() {
      let filter = this.value.toLowerCase();
      let rows = document.querySelectorAll("#courseTable tbody tr");
      rows.forEach(row => {
          row.style.display = row.textContent.toLowerCase().includes(filter) ? "" : "none";
      });
  });

  // Search filter for enrollments
  document.getElementById("enrollmentSearch").addEventListener("keyup", function() {
      let filter = this.value.toLowerCase();
      let rows = document.querySelectorAll("#enrollmentTable tbody tr");
      rows.forEach(row => {
          row.style.display = row.textContent.toLowerCase().includes(filter) ? "" : "none";
      });
  });

  // Fill modals with existing data
  function fillStudentModal(id, name, email) {
    document.getElementById('updateStudentId').value = id;
    document.getElementById('updateStudentName').value = name;
    document.getElementById('updateStudentEmail').value = email;
  }
  function fillCourseModal(id, name, description, credits) {
    document.getElementById('updateCourseId').value = id;
    document.getElementById('updateCourseName').value = name;
    document.getElementById('updateCourseDescription').value = description;
    document.getElementById('updateCourseCredits').value = credits;
  }
</script>
</div>
</body>
</html>
