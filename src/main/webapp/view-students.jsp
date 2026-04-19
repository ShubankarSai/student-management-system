<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Students</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	background: linear-gradient(to right, #b8c6db, #8fa4c0);
	min-height: 100vh;
}


</style>
</head>
<body>
	<div class="container mt-4 p-4 rounded shadow-sm"
		style="background: rgba(255, 255, 255, 0.6);">

		<nav class="navbar shadow mb-4"
			style="background: linear-gradient(45deg, #4e73df, #6c8ebf);">
			<div class="container">
				<span class="navbar-brand text-white fw-bold fs-4">
					Student Management System </span>
			</div>
		</nav>

		<div class="mt-4 mb-3">
			<h2 class="fw-bold px-4 py-2 d-inline-block rounded shadow-sm"
				style="background: #ffffffcc; color: #2c3e50;">Student List</h2>
		</div>

		<!-- SEARCH BOX -->
		<form action="student" method="get" class="mb-4">
			<div class="input-group shadow">
				<input type="text" name="keyword" class="form-control"
					placeholder="Search students">
				<button class="btn btn-primary">Search</button>
			</div>
		</form>

		<c:if test="${not empty param.keyword}">
			<a href="student" class="btn btn-secondary mb-3">← List</a>
		</c:if>

		<!-- Add Student Button -->
		<a href="add-student.jsp" class="btn btn-primary mb-3">+ Add
			Student</a>

		<c:if test="${param.msg == 'added'}">
			<div class="alert alert-success">Student added successfully!</div>
		</c:if>

		<c:if test="${param.msg == 'updated'}">
			<div class="alert alert-success">Student updated successfully!</div>
		</c:if>

		<c:if test="${param.msg == 'deleted'}">
			<div class="alert alert-danger">Student deleted successfully!</div>
		</c:if>

		<!-- Table -->
		<div class="table-container shadow rounded overflow-hidden">
			<table class="table table-bordered table-hover mb-0">
				<thead class="table-dark text-center">
					<tr>
						<th>S.No</th>
						<th>ID</th>
						<th>Name</th>
						<th>Email</th>
						<th>Course</th>
						<th>Actions</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="s" items="${studentList}" varStatus="i">
						<tr>
							<td>${i.index + 1}</td>
							<td>${s.id}</td>
							<td>${s.name}</td>
							<td>${s.email}</td>
							<td>${s.course}</td>

							<td><a href="student?action=edit&id=${s.id}"
								class="btn btn-warning btn-sm">Edit</a> <a
								href="student?action=delete&id=${s.id}"
								class="btn btn-danger btn-sm"
								onclick="return confirm('Are you sure you want to delete?');">
									Delete </a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div class="d-flex justify-content-center mt-4">

			<nav>
				<ul class="pagination">

					<!-- Previous -->
					<c:if test="${currentPage > 1}">
						<li class="page-item"><a class="page-link"
							href="student?page=${currentPage - 1}"> Previous </a></li>
					</c:if>

					<!-- Page Numbers -->
					<c:forEach begin="1" end="${totalPages}" var="i">
						<li class="page-item ${i == currentPage ? 'active' : ''}"><a
							class="page-link" href="student?page=${i}"> ${i} </a></li>
					</c:forEach>

					<!-- Next -->
					<c:if test="${currentPage < totalPages}">
						<li class="page-item"><a class="page-link"
							href="student?page=${currentPage + 1}"> Next </a></li>
					</c:if>

				</ul>
			</nav>

		</div>

	</div>

</body>
</html>