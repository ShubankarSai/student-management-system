package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.StudentDAO;
import model.Student;

@WebServlet("/student")
public class StudentServlet extends HttpServlet{
	private StudentDAO dao;
	
	public void init() {
		dao = new StudentDAO();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	    String action = req.getParameter("action");

	    //Handle actions FIRST (edit/delete)
	    if (action != null) {
	        switch (action) {
	            case "delete":
	                deleteStudent(req, resp);
	                return;

	            case "edit":
	                showEditForm(req, resp);
	                return;
	        }
	    }

	    //Handle search
	    String keyword = req.getParameter("keyword");

	    if (keyword != null && !keyword.trim().isEmpty()) {
	        listStudentBySearch(req, resp, keyword);
	        return;
	    }

	    //Default → pagination list
	    listStudents(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		
		if(action == null) {
			action = "list";
		}
		
		switch(action) {
			case "insert":
				insertStudent(req, resp);
				break;
			case "edit":
		        showEditForm(req, resp);
		        break;
		    case "update":
		        updateStudent(req, resp);
		        break;
		    case "delete":
		        deleteStudent(req, resp);
		        break;
			default:
				listStudents(req, resp);
				break;
		}
	}

	private void listStudents(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {

	    int page = 1;
	    int recordsPerPage = 10;

	    if(req.getParameter("page") != null){
	        page = Integer.parseInt(req.getParameter("page"));
	    }

	    int start = (page - 1) * recordsPerPage;

	    List<Student> list = dao.getStudentsByPage(start, recordsPerPage);
	    int totalRecords = dao.getStudentCount();

	    int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

	    req.setAttribute("studentList", list);
	    req.setAttribute("currentPage", page);
	    req.setAttribute("totalPages", totalPages);

	    req.getRequestDispatcher("view-students.jsp").forward(req, resp);
	}

	private void insertStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String course = req.getParameter("course");
		
		Student s = new Student(name, email, course);
		dao.insertStudent(s);
		
		resp.sendRedirect("student?msg=added");
	}
	
	private void deleteStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		dao.deleteStudent(id);
		
		resp.sendRedirect("student?msg=deleted");
	}
	
	private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		Student student = dao.selectStudentById(id);
		
		req.setAttribute("student", student);
		req.getRequestDispatcher("edit-student.jsp").forward(req, resp);
	}
	
	private void updateStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String course = req.getParameter("course");
		
		Student s = new Student(name, email, course);
		s.setId(id);
		
		dao.updateStudent(s);
		
		resp.sendRedirect("student?msg=updated");
	}
	
	private void listStudentBySearch(HttpServletRequest req, HttpServletResponse resp, String keyword) throws ServletException, IOException{
		
		List<Student> list = dao.searchStudents(keyword);
		req.setAttribute("studentList", list);
		req.getRequestDispatcher("view-students.jsp").forward(req, resp);
		
	}
}
