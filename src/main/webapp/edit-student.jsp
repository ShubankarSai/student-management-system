<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Student"%>

<%
    Student s = (Student) request.getAttribute("student");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Student</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">

<style>
body {
	background: linear-gradient(to right, #eef2f3, #dfe9f3);
	min-height: 100vh;
}

.card {
	border-radius: 12px;
}

.header {
	background: linear-gradient(to right, #2c3e50, #4ca1af);
	color: white;
	padding: 15px;
	border-radius: 12px 12px 0 0;
	text-align: center;
}
</style>
</head>

<body>

	<!-- Navbar -->
	<nav class="navbar navbar-dark bg-dark shadow">
		<div class="container">
			<span class="navbar-brand mb-0 h1"> Student Management System
			</span>
		</div>
	</nav>

	<!-- Form Card -->
	<div class="container mt-5">
		<div class="card shadow-lg">

			<div class="header">
				<h3>Edit Student</h3>
			</div>

			<div class="card-body p-4">

				<form action="student" method="post">
					<input type="hidden" name="action" value="update"> <input
						type="hidden" name="id" value="<%= s.getId() %>">

					<div class="mb-3">
						<label class="form-label">Name</label> <input type="text"
							name="name" value="<%= s.getName() %>" class="form-control"
							required>
					</div>

					<div class="mb-3">
						<label class="form-label">Email</label> <input type="email"
							name="email" value="<%= s.getEmail() %>" class="form-control"
							required>
					</div>

					<div class="mb-3">
						<label class="form-label">Course</label> <input type="text"
							name="course" value="<%= s.getCourse() %>" class="form-control"
							required>
					</div>

					<div class="d-flex justify-content-between">
						<a href="student" class="btn btn-secondary">⬅ Back</a>
						<button type="submit" class="btn btn-success">Update
							Student</button>
					</div>

				</form>

			</div>
		</div>
	</div>

</body>
</html>