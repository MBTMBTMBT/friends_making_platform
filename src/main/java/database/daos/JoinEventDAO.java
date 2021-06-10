package database.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import database.supports.HibernateUtil;
import database.supports.JDBCTool;
import database.tables.Event;
import database.tables.JoinEvent;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mysql.cj.Query;

public class JoinEventDAO {

	
	public static List<JoinEvent> getAllJoinEvent() {
		List<JoinEvent> j  = null;
			
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			j = session.createQuery("from JoinEvent").list();
        } catch (Exception e) {
          // e.printStackTrace();
        }
		
		return j;
	}
	
	
	public static void saveJoinEvent(JoinEvent e) {
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

	
	public static JoinEvent getJoinEventByKey(int Uid,int EventLocationID) {
		JoinEvent event = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			event = (JoinEvent)session.createQuery("from JoinEvent where Uid = "+ Uid + " and EventLocationID = "+ EventLocationID).uniqueResult();
        } catch (Exception e) {
           //e.printStackTrace();
        }
		return event;
	}

	public static List<JoinEvent> getJoinEventByLocation(int EventLocationID) {
		List<JoinEvent> events = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			events = session.createQuery("from JoinEvent where EventLocationID = "+ EventLocationID).list();
		} catch (Exception e) {
			return null;
		}
		return events;
	}

	public static List<JoinEvent> getJoinEventByUid(int uid) {
		List<JoinEvent> events = new LinkedList<>();
		try {
			Connection connection = JDBCTool.getConnection();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM join_event WHERE Uid = ?;");
			ps.setInt(1, uid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				JoinEvent joinEvent = new JoinEvent();
				joinEvent.setEventLocationID(rs.getInt("EventLocationID"));
				joinEvent.setUid(rs.getInt("Uid"));
				joinEvent.setTime(rs.getString("Time"));
				events.add(joinEvent);
			}
			connection.close();
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			events = null;
		}
		return events;
	}

	public static void deleteJoinEventByKey(int Uid, int EventLocationID, String Time) {
		JoinEvent event = new JoinEvent();
		event.setUid(Uid); event.setEventLocationID(EventLocationID); event.setTime(Time);
		/*
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction=session.beginTransaction();
			event.setUid(Uid);
			event.setEventLocationID(EventLocationID);
			session.delete(event);
			transaction.commit();
	    	session.close();

        } catch (Exception e) {
          // e.printStackTrace();
        }*/
		try {
			Connection connection = JDBCTool.getConnection();
			String sql = "DELETE FROM join_event WHERE Uid = " + Uid + " and EventLocationID = " + EventLocationID + " and Time = '" + Time + "';";
			System.out.println(sql);
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.executeUpdate();
			pst.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void updateJoinEvent(JoinEvent e) {
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction=session.beginTransaction();
			session.update(e);
			transaction.commit();
	    	session.close();
			
        } catch (Exception m) {
           //m.printStackTrace();
        }
		
	}


}
