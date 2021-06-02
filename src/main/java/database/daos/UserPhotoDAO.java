package database.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.supports.HibernateUtil;
import database.tables.UserPhoto;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mysql.cj.Query;

public class UserPhotoDAO {

	
	public static List<UserPhoto> getAllUserPhoto() {
		List<UserPhoto> l  = null;
			
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			l = session.createQuery("from UserPhoto").list();
        } catch (Exception e) {
           e.printStackTrace();
        }
		
		return l;
	}
	
	
	public static void saveUserPhoto(UserPhoto e) {
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

	
	public static UserPhoto getUserPhotoByKey(int uid) {
		UserPhoto l = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			l = (UserPhoto)session.createQuery("from UserPhoto where Uid = "+ uid).uniqueResult();
        } catch (Exception e) {
           e.printStackTrace();
        }
		return l;

	}
	
	
	public static void deleteUserPhotoByKey(int uid) {
		UserPhoto l = new UserPhoto();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction=session.beginTransaction();
			l.setUid(uid);
			session.delete(l);
			transaction.commit();
	    	session.close();
			
        } catch (Exception e) {
           e.printStackTrace();
        }
	}
	
	
	
	public static void updateUserPhoto(UserPhoto e) {
		
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

