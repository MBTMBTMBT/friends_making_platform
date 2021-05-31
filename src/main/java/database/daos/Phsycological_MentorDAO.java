package database.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mysql.cj.Query;

public class Phsycological_MentorDAO {
	
	public static List<Phsycological_Mentor> getAllPhsycological_Mentor() {
		List<Phsycological_Mentor> p  = null;
			
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			p = session.createQuery("from phsycological_mentor").list();
        } catch (Exception e) {
           e.printStackTrace();
        }
		
		return p;
	}
	
	
	public static void savePhsycological_Mentor(Phsycological_Mentor e) {
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

	
	public static Phsycological_Mentor getPhsycological_MentorByID(int id) {
		Phsycological_Mentor p = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			p = (Phsycological_Mentor)session.createQuery("from phsycological_mentor where MentorNumber = "+id).uniqueResult();
        } catch (Exception e) {
           e.printStackTrace();
        }
		return p;

	}
	
	public static Phsycological_Mentor getPhsycological_MentorBySystemID(int id) {
		Phsycological_Mentor p = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			p = (Phsycological_Mentor)session.createQuery("from phsycological_mentor where SystemID = "+id).uniqueResult();
        } catch (Exception e) {
           e.printStackTrace();
        }
		return p;

	}
	
	
	public static void deletePhsycological_MentorByID(int id) {
		Phsycological_Mentor p = new Phsycological_Mentor();
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
	
	
	
	public static void updatePhsycological_Mentore(Phsycological_Mentor e) {
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction=session.beginTransaction();
			session.update(e);
			transaction.commit();
	    	session.close();
			
        } catch (Exception m) {
           m.printStackTrace();
        }
		
	}


}
