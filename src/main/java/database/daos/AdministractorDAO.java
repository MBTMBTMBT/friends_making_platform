package database.daos;
import java.util.List;

import database.supports.HibernateUtil;
import database.tables.Administrator;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AdministractorDAO {
	
	public static List<Administrator> getAllAdministractor() {
		List<Administrator> administrator = null;
			
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			administrator = session.createQuery("from Administractor").list();
        } catch (Exception e) {
           e.printStackTrace();
        }
		
		return administrator;
	}
	
	
	public static void saveAdministrator(Administrator e) {
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

	
	public static Administrator getAdministractorByID(int id) {
		Administrator administrator = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			administrator = (Administrator)session.createQuery("from Administractor where AdminNumber = "+id).uniqueResult();
        } catch (Exception e) {
           e.printStackTrace();
        }
		return administrator;

	}
	
	
	public static void deleteAdministractorByID(int id) {
		Administrator administrator = new Administrator();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction=session.beginTransaction();
			administrator.setAdminNumber(id);
			session.delete(administrator);
			transaction.commit();
	    	session.close();
			
        } catch (Exception e) {
           e.printStackTrace();
        }
	}
	
	
	
	public static void updateAdministractor(Administrator e) {
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction=session.beginTransaction();
			session.update(e);
			transaction.commit();
	    	session.close();
			
        } catch (Exception m) {
           m.printStackTrace();
        }
		
	}

	public static Administrator getAdministractorBySystemID(int id) {
		Administrator administrator = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			administrator = (Administrator)session.createQuery("from Administractor where SystemID = "+id).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return administrator;
	}
}
