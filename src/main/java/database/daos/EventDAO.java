package database.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.mysql.cj.jdbc.JdbcConnection;
import database.standarizedTables.StdSports;
import database.supports.HibernateUtil;
import database.supports.JDBCTool;
import database.tables.Event;
import database.tables.Likes;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mysql.cj.Query;

public class EventDAO {
	
	public static List<Event> getAllEvent() {
		List<Event> event  = null;
			
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			event = session.createQuery("from Event").list();
        } catch (Exception e) {
           e.printStackTrace();
        }
		
		return event;
	}
	
	
	public static void saveEvent(Event e) {
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

	public static Event getEventByKey(int LocationID, String time) {
		Event event = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			event = (Event)session.createQuery("from Event where LocationID = "+ LocationID + "and Time = '"+ time + "'").uniqueResult();
        } catch (Exception e) {
           e.printStackTrace();
        }
		return event;
	}

	public static List<Event> getEventByLocation(int locationID) {
		List<Event> events = new LinkedList<>();
		/*
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			System.out.println("from Event where LocationID = "+ LocationID);
			events = session.createQuery("from Event where LocationID = "+ LocationID).list();
		} catch (Exception e) {
			e.printStackTrace();
		} */
		try {
			Connection connection = JDBCTool.getConnection();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM event WHERE LocationID = ?;");
			ps.setInt(1, locationID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				// int Uid1 = rs.getInt("Uid1");
				// int Uid2 = rs.getInt("Uid2");
				Event event = new Event();
				event.setActivities(rs.getString("Activities"));
				event.setTime(rs.getString("Time"));
				event.setNumberofparticipants(rs.getInt("Number_of_participants"));
				event.setLocationID(rs.getInt("LocationID"));
				events.add(event);
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
	
	public static void deleteEventByKey(int LocationID, String time) {
		Event event;
		try {
			Connection connection = JDBCTool.getConnection();
			String sql = "DELETE FROM event WHERE LocationID = " + LocationID + " and time = '" + time + "';";
			System.out.println(sql);
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.executeUpdate();
			pst.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			event = getEventByKey(LocationID, time);
			Transaction transaction=session.beginTransaction();
			event.setLocationID(LocationID);
			event.setTime(time);
			session.delete(event);
			transaction.commit();
	    	session.close();
        } catch (Exception e) {
           e.printStackTrace();
        } */
	}
	
	
	
	public static void updateEvent(Event e) {
		
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
