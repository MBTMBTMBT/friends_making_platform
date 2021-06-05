package database.supports;

import database.dynamicDAOs.DynamicDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class DataBaseDelete extends DynamicDAO {
    public void deleteAllEmployees() {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.createSQLQuery("DELETE FROM Employee").executeUpdate();
            transaction.commit();
        } catch (Exception exp) {
            if (transaction != null) {
                transaction.rollback();
            }
            exp.printStackTrace();
        }
    }
}
