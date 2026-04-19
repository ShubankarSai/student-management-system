# Student Management System

## Overview

The Student Management System is a web-based application developed using Java Servlets, JSP, JDBC, and MySQL. It follows the MVC (Model-View-Controller) architecture to perform complete CRUD (Create, Read, Update, Delete) operations on student records.

The application provides a user-friendly interface for managing student data, including features such as searching and pagination for efficient data handling.

---

## Features

* Add new student records
* View all students in a structured table
* Update existing student details
* Delete student records with confirmation
* Search students by name
* Pagination (10 records per page)
* Sorted display of student records (alphabetical order)
* Responsive user interface using Bootstrap
* MVC architecture with separation of concerns

---

## Technology Stack

* **Backend:** Java (Servlets, JDBC)
* **Frontend:** JSP, HTML, CSS, Bootstrap
* **Database:** MySQL
* **Server:** Apache Tomcat
* **IDE:** Eclipse

---

## Project Structure

Student-Management-System/
│
├── src/
│   ├── controller/
│   │   └── StudentServlet.java
│   ├── dao/
│   │   └── StudentDAO.java
│   └── model/
│       └── Student.java
│
├── WebContent/ (or src/main/webapp/)
│   ├── add-student.jsp
│   ├── edit-student.jsp
│   ├── view-students.jsp
│   └── WEB-INF/
│
├── .classpath
├── .project
└── build/

---

## Database Configuration

Create a MySQL database:

```sql
CREATE DATABASE student_management;
```

Create the students table:

```sql
CREATE TABLE students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100),
    course VARCHAR(100)
);
```

Update your database credentials in `StudentDAO.java`:

```java
private String jdbcURL = "jdbc:mysql://localhost:3306/student_management";
private String jdbcUsername = "root";
private String jdbcPassword = "your_password";
```

---

## How to Run the Project

1. Clone the repository:

   ```
   git clone https://github.com/your-username/student-management-system.git
   ```

2. Import the project into Eclipse:

   * File → Import → Existing Projects into Workspace

3. Configure Apache Tomcat:

   * Add Tomcat server in Eclipse
   * Right-click project → Run on Server

4. Ensure MySQL is running and database is configured.

5. Access the application:

   ```
   http://localhost:8080/Student_Management_System/
   ```

---

## Application Flow

* The user interacts with JSP pages (View layer)
* Requests are handled by `StudentServlet` (Controller)
* Business logic and database operations are managed by `StudentDAO`
* Data is stored and retrieved from MySQL database

---

## Key Functionalities

### CRUD Operations

* Insert student data using forms
* Retrieve and display all records
* Update student details via edit form
* Delete records with confirmation prompt

### Search

* Search functionality implemented using SQL `LIKE` operator
* Allows partial matching of student names

### Pagination

* Displays limited records per page
* Improves performance and usability
* Navigation through Previous, Next, and page numbers

---

## Improvements and Enhancements

Planned or possible future improvements:

* User authentication (login/logout)
* Role-based access control
* Input validation (client-side and server-side)
* Sorting by multiple fields
* REST API integration
* Deployment on cloud platforms

---

## Learning Outcomes

* Understanding of MVC architecture
* Hands-on experience with Servlets and JSP
* JDBC integration with MySQL
* Implementation of pagination and search
* Building responsive UI with Bootstrap
* Structuring scalable Java web applications

---

## Author

Shubhankar Sai
