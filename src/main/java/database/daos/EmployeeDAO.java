package database.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.supports.HibernateUtil;
import database.tables.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mysql.cj.Query;

public class EmployeeDAO {
	
	public static List<Employee> getAllEmployee() {
		List<Employee> employee  = null;
			
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			employee = session.createQuery("from employee").list();
        } catch (Exception e) {
           e.printStackTrace();
        }
		
		return employee;
	}
	
	
	public static void saveEmployee(Employee e) {
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

	
	public static Employee getEmployeeByID(int id) {
		Employee employee = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			employee = (Employee)session.createQuery("from employee where EmployeeID = "+id).uniqueResult();
        } catch (Exception e) {
           e.printStackTrace();
        }
		return employee;

	}
	
	
	public static void deleteEmployeeByID(int id) {
		Employee employee = new Employee();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction=session.beginTransaction();
			employee.setEmployeeID(id);
			session.delete(employee);
			transaction.commit();
	    	session.close();
			
        } catch (Exception e) {
           e.printStackTrace();
        }
	}
	
	
	
	public static void updateEmployee(Employee e) {
		
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
