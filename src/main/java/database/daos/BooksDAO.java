package database.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.supports.HibernateUtil;
import database.tables.Books;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mysql.cj.Query;

public class BooksDAO {
	
	public static List<Books> getAllBooks() {
		List<Books> l  = null;
			
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			l = session.createQuery("from Books").list();
        } catch (Exception e) {
           e.printStackTrace();
        }
		
		return l;
	}
	
	
	public static void saveBooks(Books e) {
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

	
	public static Books getBooksByKey(int bid,int uid) {
		Books l = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			l = (Books)session.createQuery("from Books where Bid = "+ bid + " and Uid = "+uid).uniqueResult();
        } catch (Exception e) {
           e.printStackTrace();
        }
		return l;

	}
	
	
	public static void deleteBooksByKey(int bid,int uid) {
		Books l = new Books();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction=session.beginTransaction();
			l.setUid(uid);
			l.setBid(bid);
			session.delete(l);
			transaction.commit();
	    	session.close();
			
        } catch (Exception e) {
           e.printStackTrace();
        }
	}
	
	
	
	public static void updateBooks(Books e) {
		
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


