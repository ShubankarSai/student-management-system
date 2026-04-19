<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Management System</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
	body {
    	background: linear-gradient(to right, #b8c6db, #8fa4c0);
    	min-height: 100vh;
	}
</style>
</head>
<body>
	<div class="container mt-5">
		<div class="card shadow p-4">
		

    		<h3 class="text-center mb-4 p-3 text-white rounded"
    			style="background: linear-gradient(45deg, #0f2027, #2c5364);">
    			Add Student
			</h3>
			
			<form action="student" method="post">
				<input type="hidden" name="action" value="insert">
				
				<div class="mb=3">
					<label class="form-label">Name</label>
					<input type="text" name="name" class="form-control" required>
				</div>
				
				<div class="mb=3">
					<label class="form-label">Email</label>
					<input type="email" name="email" class="form-control" required>
				</div>
				
				<div class="mb=3">
					<label class="form-label">Course</label>
					<input type="text" name="course" class="form-control" required>
				</div><br>
				
				<div class="d-flex justify-content-between">
					<a href="student" class="btn btn-secondary">Back</a>
					<button type="submit" class="btn btn-success">Add Student</button>
				</div>
			</form>
		</div>
		
	</div>
	
</body>
</html>