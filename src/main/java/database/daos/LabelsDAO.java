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

public class LabelsDAO {
	
	public static List<Labels> getAllLabels() {
		List<Labels> l  = null;
			
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			l = session.createQuery("from Labels").list();
        } catch (Exception e) {
           e.printStackTrace();
        }
		
		return l;
	}
	
	
	public static void saveLabels(Labels e) {
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

	
	public static Labels getLabelsByKey(int id) {
		Labels l = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			l = (Labels)session.createQuery("from Labels where Serial = " + id).uniqueResult();
        } catch (Exception e) {
           e.printStackTrace();
        }
		return l;

	}
	
	
	public static void deleteLabelsByKey(int id) {
		Labels l = new Labels();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction=session.beginTransaction();
			l.setSerial(id);
			session.delete(l);
			transaction.commit();
	    	session.close();
			
        } catch (Exception e) {
           e.printStackTrace();
        }
	}
	
	
	
	public static void updateLabels(Labels e) {
		
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

