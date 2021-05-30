package database.daos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.supports.HibernateUtil;
import database.tables.Administractor;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mysql.cj.Query;

public class AdministractorDAO {
	
	public static List<Administractor> getAllAdministractor() {
		List<Administractor> administractor  = null;
			
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			administractor = session.createQuery("from administractor").list();
        } catch (Exception e) {
           e.printStackTrace();
        }
		
		return administractor;
	}
	
	
	public static void saveAdministractor(Administractor e) {
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

	
	public static Administractor getAdministractorByID(int id) {
		Administractor administractor = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			administractor = (Administractor)session.createQuery("from administractor where AdminNumber = "+id).uniqueResult();
        } catch (Exception e) {
           e.printStackTrace();
        }
		return administractor;

	}
	
	
	public static void deleteAdministractorByID(int id) {
		Administractor administractor = new Administractor();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction=session.beginTransaction();
			administractor.setAdminNumber(id);
			session.delete(administractor);
			transaction.commit();
	    	session.close();
			
        } catch (Exception e) {
           e.printStackTrace();
        }
	}
	
	
	
	public static void updateAdministractor(Administractor e) {
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction=session.beginTransaction();
			session.update(e);
			transaction.commit();
	    	session.close();
			
        } catch (Exception m) {
           m.printStackTrace();
        }
		
	}

	public static Administractor getAdministractorBySystemID(int id) {
		Administractor administractor = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			administractor = (Administractor)session.createQuery("from administractor where SystemID = "+id).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return administractor;

	}
}
