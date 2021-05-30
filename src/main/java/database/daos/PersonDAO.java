package database.daos;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PersonDAO {
	
	public static List<Person> getAllPerson() {
		List<Person> person = null;
			
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			person = session.createQuery("from person").list();
        } catch (Exception e) {
           e.printStackTrace();
        }
		
		return person;
	}
	
	
	public static void Person(Person e) {
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
			person = (Person)session.createQuery("from person where SystemID = "+id).uniqueResult();
        } catch (Exception e) {
           e.printStackTrace();
        }
		return person;

	}
	
	
	public static void deletePersonByID(int id) {
		Person person = new Person();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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


	

}
