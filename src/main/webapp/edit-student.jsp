<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Student</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
	body {
    	background: linear-gradient(to right, #eef2f3, #dfe9f3);
    	min-height: 100vh;
	}
</style>
</head>
<body>
	<%@ page import="model.Student" %>
	<%
		Student s = (Student) request.getAttribute("student");
	%>
	
	<nav class="navbar navbar-dark bg-dark shadow">
    	<div class="container">
        	<span class="navbar-brand mb-0 h1">
            	Student Management System
        	</span>
    	</div>
	</nav>
	
	<h2>Edit Student</h2>
	
	<form action="student" method="post">
		<input type="hidden" name="action" value="update">
		<input type="hidden" name="id" value="<%= s.getId() %>">
		
		Name: <input type="text" name="name" value="<%= s.getName() %>" required> <br><br>
		Email: <input type="email" name="email" value="<%= s.getEmail() %>" required> <br><br>
		Course: <input type="text" name="course" value="<%= s.getCourse() %>" required> <br><br>
		
		<input type="submit" value="Update Student">
	</form>
	
	<br>
	<a href="student">Back to List</a>
	
</body>
</html>