package database.daos;

import database.exceptions.PersonNotExistsException;
import database.supports.JDBCTool;
import database.tables.Admin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {

	public static List<Admin> getAllAdmins() {
		List<Admin> admins = new ArrayList<Admin>();
			
		try {
			Connection conn = JDBCTool.getConnection();
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery("SELECT * FROM admin");
			
			while(rs.next()) {
				
				int id = rs.getInt("id");
				String firstname = rs.getString("firstname");
				String familyname = rs.getString("familyname");
				String password = rs.getString("password");
				String gender = rs.getString("gender");
				int age = rs.getInt("age"); 
				
				Admin a = new Admin(id,firstname,familyname,password,gender,age);
				
				admins.add(a);
			}
			
			rs.close();
			st.close();
			conn.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return admins;
	}

	public static boolean isAdmin(int uid) {
	  boolean a = false;
	  try {
	   Connection conn = JDBCTool.getConnection();
	   // Use prepared statement
	   PreparedStatement ps = conn.prepareStatement("SELECT * FROM admin WHERE id = ?;");
	   ps.setInt(1,uid);
	   ResultSet rs = ps.executeQuery();
	   // Use statement
	   /*
	   Statement st = conn.createStatement();
	   ResultSet rs = st.executeQuery("SELECT * FROM admin WHERE id = "+uid+";");
	   */
	   if(rs != null) {
	   	a = true;
	   }
	   
	  } catch (SQLException e) {
	   e.printStackTrace();
	  }
	  return a;
	 }
	
	public static Admin getAdminByID(int uid) {
		Admin admin = null;
		try {
			Connection conn = JDBCTool.getConnection();
			// Use prepared statement
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM admin WHERE id = ?;");
			ps.setInt(1,uid);
			ResultSet rs = ps.executeQuery();
			// Use statement
			/*
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM admin WHERE id = "+uid+";");
			*/
			rs.next();
			int id = rs.getInt("id");
			String firstname = rs.getString("firstname");
			String familyname = rs.getString("familyname");
			String password = rs.getString("password");
			String gender = rs.getString("gender");
			int age = rs.getInt("age"); 
			admin = new Admin(id,firstname,familyname,password,gender,age);
			rs.close();
			//st.close();
			ps.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return admin;
	}

   public static boolean adminLogin(int uid, String password) throws PersonNotExistsException {
	  boolean a = false;
	  try {
	   Connection conn = JDBCTool.getConnection();
	   // Use prepared statement
	   PreparedStatement ps = conn.prepareStatement("SELECT * FROM admin WHERE id = ?;");
	   ps.setInt(1,uid);
	   ResultSet rs = ps.executeQuery();
	   // Use statement
	   /*
	   Statement st = conn.createStatement();
	   ResultSet rs = st.executeQuery("SELECT * FROM admin WHERE id = "+uid+";");
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
		    
	public static boolean deleteAdminByID(int uid) {
		boolean a = false ;
		try {
			Connection conn = JDBCTool.getConnection();
			Statement st = conn.createStatement();	
			int b = st.executeUpdate("DELETE FROM admin WHERE id = "+uid+";");
			a = (b>0);
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}
		
	public static boolean updateAdmin(Admin e) {
		boolean a = false ;
		try {
			Connection conn = JDBCTool.getConnection();
			Statement st = conn.createStatement();	
			int b = st.executeUpdate("UPDATE admin SET firstname="+e.getFirstname()+",familyname="+e.getFamilyname()+",password="+e.password()+",gender="+e.getGender()+",age="+e.getAge()+" WHERE id = "+ e.getId() +";");
			
			a = (b>0);
			st.close();
			conn.close();
			
		} catch (SQLException m) {
			m.printStackTrace();
		}
		return a;
	}
	
	public static boolean insertAdmin(Admin e) {
		boolean a = false ;
		try {
			Connection conn = JDBCTool.getConnection();
			Statement st = conn.createStatement();	
			int b = st.executeUpdate("INSERT INTO admin VALUES ("+e.getId()+","+e.getFirstname()+","+e.getFamilyname()+","+e.getGender()+","+e.getAge()+");");
			a = (b>0);
			st.close();
			conn.close();
			
		} catch (SQLException m) {
			m.printStackTrace();
		}
		return a;
	}
}


