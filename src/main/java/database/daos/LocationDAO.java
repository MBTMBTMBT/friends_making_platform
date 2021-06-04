package database.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.supports.HibernateUtil;
import database.tables.Location;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mysql.cj.Query;

public class LocationDAO {
	
	public static List<Location> getAllLocation() {
		List<Location> l  = null;
			
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			l = session.createQuery("from Location").list();
			session.close();
        } catch (Exception e) {
           e.printStackTrace();
        }
		return l;
	}
	
	
	public static void saveLocation(Location e) {
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

	
	public static Location getLocationByKey(int lid,int uid) {
		Location l = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			l = (Location)session.createQuery("from Location where Lid = "+ lid + " and Uid = "+uid).uniqueResult();
			session.close();
        } catch (Exception e) {
           e.printStackTrace();
        }
		return l;

	}
	
	
	public static void deleteLocationByKey(int lid,int uid) {
		Location l = new Location();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction=session.beginTransaction();
			l.setUid(uid);
			l.setLid(lid);
			session.delete(l);
			transaction.commit();
	    	session.close();
        } catch (Exception e) {
           e.printStackTrace();
        }
	}
	
	
	
	public static void updateLocation(Location e) {
		
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
			list = (List<Object>) session.createQuery("from Location where Uid = "+ userID).list();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}


