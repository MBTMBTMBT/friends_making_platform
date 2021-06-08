package database.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import database.standarizedTables.LabelObject;
import database.standarizedTables.StdSports;
import database.supports.HibernateUtil;
import database.supports.JDBCTool;
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

	public static List<Likes> getLikesByFirstKey(int uid1) {
		List<Likes> list = new LinkedList<>();
		try {
			Connection connection = JDBCTool.getConnection();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM likes WHERE Uid1 = ?;");
			ps.setInt(1, uid1);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				int Uid1 = rs.getInt("Uid1");
				int Uid2 = rs.getInt("Uid2");
				Likes s = new Likes(Uid1,Uid2,"");
				list.add(s);
			}
			connection.close();
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			list = null;
		}
		// for (LabelObject each: list) System.out.println((each.getLabelId()));
		return list;
	}

	public static List<Likes> getLikesBySecondKey(int uid2) {
		List<Likes> list = new LinkedList<>();
		try {
			Connection connection = JDBCTool.getConnection();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM likes WHERE Uid2 = ?;");
			ps.setInt(1, uid2);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				int Uid1 = rs.getInt("Uid1");
				int Uid2 = rs.getInt("Uid2");
				Likes s = new Likes(Uid1,Uid2,"");
				list.add(s);
			}
			connection.close();
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			list = null;
		}
		// for (LabelObject each: list) System.out.println((each.getLabelId()));
		return list;
	}

	public static void deleteLikesByKey(int uid1,int uid2) {
		/*
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Likes l = new Likes();
			Transaction transaction=session.beginTransaction();
			l.setUid1(uid1);
			l.setUid2(uid2);
			l.setConfession("");  // todo: all the delete methods might have some problem?
			session.delete(l);
			transaction.commit();
	    	session.close();
        } catch (Exception e) {
           e.printStackTrace();
        }
        */
		try {
			Connection conn = JDBCTool.getConnection();
			Statement st = conn.createStatement();
			// System.out.println("DELETE FROM likes WHERE Uid1 = " + uid1 + "and Uid2 = " + uid2 + ";");
			st.executeUpdate("DELETE FROM likes WHERE Uid1 = " + uid1 + " and Uid2 = " + uid2 + ";");
			st.close();
			conn.close();
		} catch (SQLException e) {
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

	public static void main(String[] args) {
		deleteLikesByKey(3037, 2515);
	}
}
