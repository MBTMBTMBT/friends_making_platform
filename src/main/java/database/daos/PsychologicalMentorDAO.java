package database.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import database.supports.HibernateUtil;
import database.supports.JDBCTool;
import database.tables.Likes;
import database.tables.PsychologicalMentor;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mysql.cj.Query;

public class PsychologicalMentorDAO {
	
	public static List<PsychologicalMentor> getAllPhsycological_Mentor() {
		List<PsychologicalMentor> p  = null;
			
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			p = session.createQuery("from PsychologicalMentor").list();
        } catch (Exception e) {
           e.printStackTrace();
        }
		
		return p;
	}
	
	
	public static void savePhsycologicalMentor(PsychologicalMentor e) {
		Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(e);
            transaction.commit();
        } catch (Exception exp) {
            if (transaction != null) {
                transaction.rollback();
            }
            exp.printStackTrace();
        }
	}

	
	public static PsychologicalMentor getPhsycologicalMentorByID(int id) {
		PsychologicalMentor p = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			p = (PsychologicalMentor)session.createQuery("from PsychologicalMentor where MentorNumber = "+id).uniqueResult();
        } catch (Exception e) {
           e.printStackTrace();
        }
		return p;

	}
	
	public static PsychologicalMentor getPsychologicalMentorBySystemID(int id) {
		PsychologicalMentor p = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			p = (PsychologicalMentor)session.createQuery("from PsychologicalMentor where SystemID = "+id).uniqueResult();
        } catch (Exception e) {
           e.printStackTrace();
        }
		return p;

	}
	
	
	public static void deletePhsycological_MentorByID(int id) {
		PsychologicalMentor p = new PsychologicalMentor();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction=session.beginTransaction();
			p.setMentorNumber(id);
			session.delete(p);
			transaction.commit();
	    	session.close();
			
        } catch (Exception e) {
           e.printStackTrace();
        }
	}
	
	
	
	public static void updatePsychologicalMentor(PsychologicalMentor e) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction=session.beginTransaction();
			session.update(e);
			transaction.commit();
	    	session.close();
        } catch (Exception m) {
           m.printStackTrace();
        }
	}


	public static List<PsychologicalMentor> getMentorsWIthAgeRangeAndGenOrient(int ageRange, String genderOrient) {
		if (ageRange < 20) ageRange = 10;
		if (ageRange < 80) ageRange = 20;
		else ageRange = 80;
		List<PsychologicalMentor> list = new LinkedList<>();
		String sql = "SELECT * FROM phsycological_mentor WHERE AgeRangeInRange = ? and GenderOrientationInCharge = ?;";
		try {
			Connection connection = JDBCTool.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, ageRange);
			ps.setString(2, genderOrient);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				PsychologicalMentor mentor = new PsychologicalMentor();
				mentor.setSystemID(rs.getInt("SystemID"));
				mentor.setEmployeeID(rs.getInt("EmployeeID"));
				mentor.setMentorNumber(rs.getInt("MentorNumber"));
				mentor.setAgeRangeInRange(rs.getInt("AgeRangeInRange"));
				mentor.setGenderOrientationInCharge(rs.getString("GenderOrientationInCharge"));
				list.add(mentor);
			}
			connection.close();
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			list = null;
		}
		return list;
	}
}
