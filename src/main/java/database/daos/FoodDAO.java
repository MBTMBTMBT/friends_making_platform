package database.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.supports.HibernateUtil;
import database.tables.Food;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mysql.cj.Query;

public class FoodDAO {
	
	public static List<Food> getAllFood() {
		List<Food> l  = null;
			
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			l = session.createQuery("from Food").list();
			session.close();
        } catch (Exception e) {
           e.printStackTrace();
        }
		return l;
	}
	
	
	public static void saveFood(Food e) {
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

	
	public static Food getFoodByKey(int fid,int uid) {
		Food l = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			l = (Food)session.createQuery("from Food where Fid = "+ fid + " and Uid = "+uid).uniqueResult();
			session.close();
        } catch (Exception e) {
           e.printStackTrace();
        }
		return l;

	}
	
	
	public static void deleteFoodByKey(int fid,int uid) {
		Food l = new Food();
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
	
	
	
	public static void updateFood(Food e) {
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
			list = (List<Object>) session.createQuery("from Food where Uid = "+ userID).list();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
