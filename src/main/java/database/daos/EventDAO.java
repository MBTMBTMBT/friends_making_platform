package database.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.supports.HibernateUtil;
import database.tables.Event;
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

	public static List<Event> getEventByLocation(int LocationID) {
		List<Event> events = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			events = session.createQuery("from Event where LocationID = "+ LocationID).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return events;
	}
	
	public static void deleteEventByKey(int LocationID, String time) {
		Event event = new Event();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction=session.beginTransaction();
			event.setLocationID(LocationID);
			event.setTime(time);
			session.delete(event);
			transaction.commit();
	    	session.close();
			
        } catch (Exception e) {
           e.printStackTrace();
        }
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
