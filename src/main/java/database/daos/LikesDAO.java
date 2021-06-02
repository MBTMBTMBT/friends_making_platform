package database.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.supports.HibernateUtil;
import database.tables.Likes;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mysql.cj.Query;

public class LikesDAO {

	
	public static List<Likes> getAllLikes() {
		List<Likes> l  = null;
			
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			l = session.createQuery("from Likes").list();
        } catch (Exception e) {
           e.printStackTrace();
        }
		
		return l;
	}
	
	
	public static void saveLikes(Likes e) {
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

	
	public static Likes getLikesByKey(int uid1,int uid2) {
		Likes l = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			l = (Likes)session.createQuery("from Likes where Uid1 = "+ uid1 + "and Uid2 = "+uid2).uniqueResult();
        } catch (Exception e) {
           e.printStackTrace();
        }
		return l;

	}
	
	
	public static void deleteLikesByKey(int uid1,int uid2) {
		Likes l = new Likes();
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
	
	
	
	public static void updateLikes(Likes e) {
		
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
