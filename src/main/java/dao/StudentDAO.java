package dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import model.Student;

public class StudentDAO {
	private String host = System.getenv("MYSQLHOST");
	private String port = System.getenv("MYSQLPORT");
	private String database = System.getenv("MYSQLDATABASE");

	private String jdbcURL = "jdbc:mysql://" + host + ":" + port + "/" + database;
	private String jdbcUsername = System.getenv("MYSQLUSER");
	private String jdbcPassword = System.getenv("MYSQLPASSWORD");
	
	private static final String INSERT_STUDENT = "INSERT INTO students(name,email,course) VALUES(?,?,?)";
	private static final String SELECT_ALL = "SELECT * FROM students ORDER BY name ASC";
	private static final String UPDATE_STUDENT = "UPDATE students SET name=?, email=?, course=? WHERE id=?";
	private static final String DELETE_STUDENT = "DELETE FROM students WHERE id=?";
	private static final String SELECT_BY_ID = "SELECT * FROM students WHERE id=?";
	
	protected Connection getConnection() {
	    Connection con = null;
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        System.out.println("Connecting to DB: " + jdbcURL); // debug

	        con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return con;
	}
	
	//Insert
	public void insertStudent(Student student) {
		try(Connection con = getConnection(); 
				PreparedStatement ps = con.prepareStatement(INSERT_STUDENT)){
			ps.setString(1, student.getName());
			ps.setString(2, student.getEmail());
			ps.setString(3, student.getCourse());
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// update
	public void updateStudent(Student student) {
		try(Connection con = getConnection(); 
				PreparedStatement ps = con.prepareStatement(UPDATE_STUDENT)){
			ps.setString(1, student.getName());
			ps.setString(2, student.getEmail());
			ps.setString(3, student.getCourse());
			ps.setInt(4, student.getId());
			
			ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//delete
	public void deleteStudent(int id) {
		try(Connection con = getConnection(); 
				PreparedStatement ps = con.prepareStatement(DELETE_STUDENT)){
			ps.setInt(1, id);
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//select student by ID
	public Student selectStudentById(int id) {
		Student student = null;
		
		try(Connection con = getConnection(); 
				PreparedStatement ps = con.prepareStatement(SELECT_BY_ID)){
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				student = new Student();
				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setEmail(rs.getString("email"));
				student.setCourse(rs.getString("course"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return student;
		
	}
	
	// fetch all
	public List<Student> selectAllStudents(){
		List<Student> list = new ArrayList<>();
		
		try(Connection con = getConnection(); 
				PreparedStatement ps = con.prepareStatement(SELECT_ALL)){
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Student s = new Student();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setEmail(rs.getString("email"));
				s.setCourse(rs.getString("course"));
				list.add(s);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Student> searchStudents(String keyword){
		List<Student> list = new ArrayList<>();
		String sql = "SELECT * FROM students WHERE name LIKE ? OR email LIKE ? OR course LIKE ? ORDER BY LOWER(name) ASC";
		
		try(Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setString(1, "%" + keyword + "%");
			ps.setString(2, "%" + keyword + "%");
			ps.setString(3, "%" + keyword + "%");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Student s = new Student();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setEmail(rs.getString("email"));
				s.setCourse(rs.getString("course"));
				list.add(s);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Student> getStudentsByPage(int start, int total){
	    List<Student> list = new ArrayList<>();

	    try(Connection con = getConnection();
	        PreparedStatement ps = con.prepareStatement(
	            "SELECT * FROM students ORDER BY name LIMIT ?, ?")) {

	        ps.setInt(1, start);
	        ps.setInt(2, total);

	        ResultSet rs = ps.executeQuery();

	        while(rs.next()){
	            Student s = new Student();
	            s.setId(rs.getInt("id"));
	            s.setName(rs.getString("name"));
	            s.setEmail(rs.getString("email"));
	            s.setCourse(rs.getString("course"));
	            list.add(s);
	        }

	    } catch(Exception e){
	        e.printStackTrace();
	    }

	    return list;
	}
	
	public int getStudentCount(){
	    int count = 0;

	    try(Connection con = getConnection();
	        PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM students")){

	        ResultSet rs = ps.executeQuery();

	        if(rs.next()){
	            count = rs.getInt(1);
	        }

	    } catch(Exception e){
	        e.printStackTrace();
	    }

	    return count;
	}
	
}
