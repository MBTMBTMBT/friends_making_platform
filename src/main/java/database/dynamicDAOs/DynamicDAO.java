package database.dynamicDAOs;

import database.supports.HibernateUtil;
import org.hibernate.Session;

public class DynamicDAO {
    protected Session session;

    public DynamicDAO() {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeSession() {
        session.close();
        session = null;
    }
}
