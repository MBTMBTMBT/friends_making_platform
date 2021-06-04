package database.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.supports.HibernateUtil;
import database.tables.Films;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mysql.cj.Query;

public class FilmsDAO {
	
	public static List<Films> getAllFilms() {
		List<Films> l  = null;
			
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			l = session.createQuery("from StdFilms").list();
			session.close();
        } catch (Exception e) {
           e.printStackTrace();
        }
		return l;
	}
	
	
	public static void saveFilms(Films e) {
		Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(e);
            transaction.commit();
            session.close();
        } catch (Exception exp) {
            if (transaction != null) {
                transaction.rollback();
            }
            exp.printStackTrace();
        }
	}

	
	public static Films getFilmsByKey(int fid,int uid) {
		Films l = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			l = (Films)session.createQuery("from StdFilms where Fid = "+ fid + " and Uid = "+uid).uniqueResult();
			session.close();
        } catch (Exception e) {
           e.printStackTrace();
        }
		return l;

	}
	
	
	public static void deleteFilmsByKey(int fid,int uid) {
		Films l = new Films();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction=session.beginTransaction();
			l.setUid(uid);
			l.setFid(fid);
			session.delete(l);
			transaction.commit();
	    	session.close();
        } catch (Exception e) {
           e.printStackTrace();
        }
	}
	
	
	
	public static void updateFilms(Films e) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction=session.beginTransaction();
			session.update(e);
			transaction.commit();
	    	session.close();
        } catch (Exception m) {
           m.printStackTrace();
        }
		
	}

	public static List<Object> getAllValuesWithUID(int userID) {
		List<Object> list = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			list = (List<Object>) session.createQuery("from StdFilms where Uid = "+ userID).list();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}



