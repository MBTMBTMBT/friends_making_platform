package database.daos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.supports.HibernateUtil;
import database.tables.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mysql.cj.Query;

public class PersonDAO {
	
	public static List<Person> getAllPerson() {
		List<Person> person = null;
			
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			person = session.createQuery("from Person").list();
        } catch (Exception e) {
           e.printStackTrace();
        }
		
		return person;
	}
	
	
	public static void savePerson(Person e) {
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

	
	public static Person getPersonByID(int id) {
		Person person = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			person = (Person)session.createQuery("from Person where SystemID = "+id).uniqueResult();
        } catch (Exception e) {
           e.printStackTrace();
        }
		return person;

	}
	
	
	public static void deletePersonByID(int id) {
		Person person = new Person();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		    //int b = session.createQuery("delete from person where SystemID = "+id).executeUpdate();
			Transaction transaction=session.beginTransaction();
			person.setSystemID(id);
			session.delete(person);
			transaction.commit();
	    	session.close();
			
        } catch (Exception e) {
           e.printStackTrace();
        }
	}
	
	
	
	public static void updatePerson(Person e) {
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction=session.beginTransaction();
			session.update(e);
			transaction.commit();
	    	session.close();
			
        } catch (Exception m) {
           m.printStackTrace();
        }
		
	}

	public static Person getPersonByScreenName(String name) {
		Person person = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			person = (Person)session.createQuery("from Person where ScreenName = "+name).uniqueResult();
		} catch (Exception ignore) {
			// e.printStackTrace();
			return null;
		}
		return person;
	}

	public static void main(String[] args) {
		try {
			System.out.println(getPersonByScreenName("mbt"));
		} catch (Exception ignore) {
			System.out.println("cao");
		}
	}
}
