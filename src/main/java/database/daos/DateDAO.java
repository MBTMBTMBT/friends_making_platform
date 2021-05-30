package database.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.supports.HibernateUtil;
import database.tables.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mysql.cj.Query;

public class DateDAO {
	
	public static List<Date> getAllDate() {
		List<Date> date  = null;
			
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			date = session.createQuery("from date").list();
        } catch (Exception e) {
           e.printStackTrace();
        }
		
		return date;
	}
	
	
	public static void saveDate(Date e) {
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

	
	public static Date getDateByKey(int uid1,int uid2,String hdmy) {
		Date date = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			date = (Date)session.createQuery("from date where Uid1 = "+ uid1 + "and Uid2 = "+uid2+"and HHDDMMYY = "+ hdmy).uniqueResult();
        } catch (Exception e) {
           e.printStackTrace();
        }
		return date;

	}
	
	
	public static void deleteDateByKey(int uid1,int uid2,String hdmy) {
		Date date = new Date();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction=session.beginTransaction();
			date.setUid1(uid1);
			date.setUid2(uid2);
			date.setHHDDMMYY(hdmy);
			session.delete(date);
			transaction.commit();
	    	session.close();
			
        } catch (Exception e) {
           e.printStackTrace();
        }
	}
	
	
	
	public static void updateDate(Date e) {
		
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
