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

public class RelationshipDAO {

	
	public static List<Relationship> getAllRelationship() {
		List<Relationship> l  = null;
			
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			l = session.createQuery("from Relationship").list();
        } catch (Exception e) {
           e.printStackTrace();
        }
		
		return l;
	}
	
	
	public static void saveRelationship(Relationship e) {
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

	
	public static Relationship getRelationshipByKey(int uid1,int uid2) {
		Relationship l = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			l = (Relationship)session.createQuery("from Relationship where Uid1 = "+ uid1 + "and Uid2 = "+uid2).uniqueResult();
        } catch (Exception e) {
           e.printStackTrace();
        }
		return l;

	}
	
	
	public static void deleteRelationshipByKey(int uid1,int uid2) {
		Relationship l = new Relationship();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction=session.beginTransaction();
			l.setUid1(uid1);
			l.setUid2(uid2);
			session.delete(l);
			transaction.commit();
	    	session.close();
			
        } catch (Exception e) {
           e.printStackTrace();
        }
	}
	
	
	
	public static void updateRelationship(Relationship e) {
		
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

