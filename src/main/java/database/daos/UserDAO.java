package database.daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	
	public static List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
			
		try {
			Connection conn = JDBCTool.getConnection();
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery("SELECT * FROM user");
			
			while(rs.next()) {
				
				int id = rs.getInt("id");
				String firstname = rs.getString("firstname");
				String familyname = rs.getString("familyname");
				String password = rs.getString("password");
				String gender = rs.getString("gender");
				int age = rs.getInt("age"); 
				String job = rs.getString("job");
				float salary = rs.getFloat("salary");
				
				User u = new User(id,firstname,familyname,password,gender,age,job,salary);
				
				users.add(u);
			}
			
			rs.close();
			st.close();
			conn.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	
	public static boolean isUser(int uid) {
		boolean a = false;
		try {
			Connection conn = JDBCTool.getConnection();
			// Use prepared statement
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM user WHERE id = ?;");
			ps.setInt(1,uid);
			ResultSet rs = ps.executeQuery();
			// Use statement
			/*
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM user WHERE id = "+uid+";");
			*/
			if(rs != null) {
				a = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}
	
	
    public static boolean userLogin(int uid, String password) throws PersonNotExistsException {
    	boolean a= false;
		try {
			Connection conn = JDBCTool.getConnection();
			// Use prepared statement
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM user WHERE id = ?;");
			ps.setInt(1,uid);
			ResultSet rs = ps.executeQuery();
			// Use statement
			/*
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM user WHERE id = "+uid+";");
			*/
			rs.next();
			String password_true = rs.getString("password");
			if (password.equals(password_true)) {
				a = true;
			}
			rs.close();
			//st.close();
			ps.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return a;
    }
    
    
	public static User getUserByID(int uid) {
		User user = null;
		try {
			Connection conn = JDBCTool.getConnection();
			// Use prepared statement
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM user WHERE id = ?;");
			ps.setInt(1,uid);
			ResultSet rs = ps.executeQuery();
			// Use statement
			/*
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM user WHERE id = "+uid+";");
			*/
			rs.next();
			int id = rs.getInt("id");
			String firstname = rs.getString("firstname");
			String familyname = rs.getString("familyname");
			String password = rs.getString("password");
			String gender = rs.getString("gender");
			int age = rs.getInt("age"); 
			String job = rs.getString("job");
			float salary = rs.getFloat("salary");
			user = new User(id,firstname,familyname,password,gender,age,job,salary);
			rs.close();
			//st.close();
			ps.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public static boolean deleteUserByID(int uid) {
		boolean a = false ;
		try {
			Connection conn = JDBCTool.getConnection();
			Statement st = conn.createStatement();	
			int b = st.executeUpdate("DELETE FROM user WHERE id = "+uid+";");
			a = (b>0);
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}
		
	public static boolean updateUser(User e) {
		boolean a = false ;
		try {
			Connection conn = JDBCTool.getConnection();
			Statement st = conn.createStatement();	
			int b = st.executeUpdate("UPDATE user SET firstname="+e.getFirstname()+",familyname="+e.getFamilyname()+",password="+e.password()+",gender="+e.getGender()+",age="+e.getAge()+",job="+e.getJob()+",salary="+e.getSalary()+" WHERE id = "+ e.getId() +";");
			
			a = (b>0);
			st.close();
			conn.close();
			
		} catch (SQLException m) {
			m.printStackTrace();
		}
		return a;
	}
	
	public static boolean insertUser(User e) {
		boolean a = false ;
		try {
			Connection conn = JDBCTool.getConnection();
			Statement st = conn.createStatement();	
			int b = st.executeUpdate("INSERT INTO user VALUES ("+e.getId()+","+e.getFirstname()+","+e.getFamilyname()+","+e.getGender()+","+e.getAge()+","+e.getJob()+","+e.getSalary()+");");
			a = (b>0);
			st.close();
			conn.close();
			
		} catch (SQLException m) {
			m.printStackTrace();
		}
		return a;
	}
	

}
