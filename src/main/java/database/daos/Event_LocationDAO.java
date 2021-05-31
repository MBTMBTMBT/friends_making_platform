package database.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mysql.cj.Query;

public class Event_LocationDAO {
	
	public static List<Event_Location> getAllEvent_Location() {
		List<Event_Location> event_location  = null;
			
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			event_location = session.createQuery("from event_location").list();
        } catch (Exception e) {
           e.printStackTrace();
        }
		
		return event_location;
	}
	
	
	public static void saveEvent_Location(Event_Location e) {
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

	
	public static Event_Location getEvent_LocationByKey(int id) {
		Event_Location event_location = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			event_location = (Event_Location)session.createQuery("from event_location where LocationID = " + id).uniqueResult();
        } catch (Exception e) {
           e.printStackTrace();
        }
		return event_location;

	}
	
	
	public static void deleteEvent_LocationByKey(int id) {
		Event_Location event_location = new Event_Location();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction=session.beginTransaction();
			event_location.setLocationID(id);
			session.delete(event_location);
			transaction.commit();
	    	session.close();
			
        } catch (Exception e) {
           e.printStackTrace();
        }
	}
	
	
	
	public static void updateEvent_Location(Event_Location e) {
		
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

