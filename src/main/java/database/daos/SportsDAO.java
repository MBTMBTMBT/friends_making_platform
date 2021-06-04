package database.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.supports.HibernateUtil;
import database.tables.Sports;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mysql.cj.Query;

public class SportsDAO {
	
	public static List<Sports> getAllSports() {
		List<Sports> l  = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			l = session.createQuery("from StdSports").list();
			session.close();
        } catch (Exception e) {
           e.printStackTrace();
        }
		return l;
	}

	public static void saveSports(Sports e) {
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

	public static Sports getSportsByKey(int sid,int uid) {
		Sports l = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			l = (Sports)session.createQuery("from StdSports where Sid = "+ sid + " and Uid = "+uid).uniqueResult();
			session.close();
        } catch (Exception e) {
           e.printStackTrace();
        }
		return l;
	}
	
	
	public static void deleteSportsByKey(int sid,int uid) {
		Sports l = new Sports();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction=session.beginTransaction();
			l.setUid(uid);
			l.setSid(sid);
			session.delete(l);
			transaction.commit();
	    	session.close();
        } catch (Exception e) {
           e.printStackTrace();
        }
	}

	public static void updateSports(Sports e) {
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
			list = (List<Object>) session.createQuery("from StdSports where Uid = "+ userID).list();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}

