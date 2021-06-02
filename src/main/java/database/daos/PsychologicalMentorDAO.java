package database.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.supports.HibernateUtil;
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
	
	
	public static void savePhsycological_Mentor(PsychologicalMentor e) {
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

	
	public static PsychologicalMentor getPhsycological_MentorByID(int id) {
		PsychologicalMentor p = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			p = (PsychologicalMentor)session.createQuery("from PsychologicalMentor where MentorNumber = "+id).uniqueResult();
        } catch (Exception e) {
           e.printStackTrace();
        }
		return p;

	}
	
	public static PsychologicalMentor getPhsycological_MentorBySystemID(int id) {
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
	
	
	
	public static void updatePhsycological_Mentore(PsychologicalMentor e) {
		
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
