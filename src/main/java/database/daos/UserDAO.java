package database.daos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.supports.HibernateUtil;
import database.tables.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mysql.cj.Query;

public class UserDAO {
	
	public static List<User> getAllUser() {
		List<User> user  = null;
			
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			user = session.createQuery("from user").list();
        } catch (Exception e) {
           e.printStackTrace();
        }
		
		return user;
	}
	
	
	public static void saveUser(User e) {
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

	
	public static User getUserByID(int id) {
		User user = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			user = (User)session.createQuery("from user where UserID = "+id).uniqueResult();
        } catch (Exception e) {
           e.printStackTrace();
        }
		return user;

	}
	
	
	public static void deleteUserByID(int id) {
		User user = new User();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction=session.beginTransaction();
			user.setUserID(id);
			session.delete(user);
			transaction.commit();
	    	session.close();
			
        } catch (Exception e) {
           e.printStackTrace();
        }
	}
	
	
	
	public static void updateUser(User e) {
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction=session.beginTransaction();
			session.update(e);
			transaction.commit();
	    	session.close();
			
        } catch (Exception m) {
           m.printStackTrace();
        }
		
	}

	public static User getUserBySystemID(int id) {
		User user = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			user = (User)session.createQuery("from user where SystemID = "+id).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;

	}
}
