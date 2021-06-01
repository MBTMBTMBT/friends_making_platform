package database.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.supports.HibernateUtil;
import database.tables.EventLocation;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mysql.cj.Query;

public class EventLocationDAO {
	
	public static List<EventLocation> getAllEvent_Location() {
		List<EventLocation> eventLocation  = null;
			
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			eventLocation = session.createQuery("from event_location").list();
        } catch (Exception e) {
           e.printStackTrace();
        }
		
		return eventLocation;
	}
	
	
	public static void saveEvent_Location(EventLocation eventLocation) {
		Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(eventLocation);
            transaction.commit();
        } catch (Exception exp) {
            if (transaction != null) {
                transaction.rollback();
            }
            exp.printStackTrace();
        }
	}

	
	public static EventLocation getEvent_LocationByKey(int id) {
		EventLocation eventLocation = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			eventLocation = (EventLocation)session.createQuery("from event_location where LocationID = " + id).uniqueResult();
        } catch (Exception e) {
           e.printStackTrace();
        }
		return eventLocation;

	}
	
	
	public static void deleteEventLocationByKey(int id) {
		EventLocation eventLocation = new EventLocation();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction=session.beginTransaction();
			eventLocation.setLocationID(id);
			session.delete(eventLocation);
			transaction.commit();
	    	session.close();
			
        } catch (Exception e) {
           e.printStackTrace();
        }
	}
	
	
	
	public static void updateEventLocation(EventLocation eventLocation) {
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction=session.beginTransaction();
			session.update(eventLocation);
			transaction.commit();
	    	session.close();
			
        } catch (Exception m) {
           m.printStackTrace();
        }
		
	}


}

