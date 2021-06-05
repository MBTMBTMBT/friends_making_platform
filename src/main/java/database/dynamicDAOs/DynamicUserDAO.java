package database.dynamicDAOs;

import database.supports.HibernateUtil;
import database.tables.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DynamicUserDAO extends DynamicDAO {
    public DynamicUserDAO() {
        super();
    }

    public List<User> getAllUsers() {
        List user  = null;
        try {
            user = session.createQuery("from User").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public void saveUser(User e) {
        Transaction transaction = null;
        try {
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

    public User getUserByID(int id) {
        User user = null;
        try {
            user = (User)session.createQuery("from User where UserID = "+id).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


    public void deleteUserByID(int id) {
        User user = new User();
        try {
            Transaction transaction = session.beginTransaction();
            user.setUserID(id);
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User e) {
        try {
            Transaction transaction=session.beginTransaction();
            session.update(e);
            transaction.commit();
        } catch (Exception m) {
            m.printStackTrace();
        }
    }

    public User getUserBySystemID(int id) {
        User user = null;
        try {
            user = (User) session.createQuery("from User where SystemID = "+id).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
