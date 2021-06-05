package database.dynamicDAOs;

import database.supports.HibernateUtil;
import database.tables.Labels;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;

public class DynamicLabelsDAO extends DynamicDAO {
    public DynamicLabelsDAO() {
        super();
    }

    public List<Labels> getAllLabels() {
        List<Labels> l  = null;
        try {
            l = session.createQuery("from Labels").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }

    public void saveLabels(Labels e) {
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

    public Labels getLabelsByKey(int id) {
        Labels l = null;
        try {
            l = (Labels)session.createQuery("from Labels where Serial = " + id).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }

    public void deleteLabelsByKey(int id) {
        Labels l = new Labels();
        try {
            Transaction transaction=session.beginTransaction();
            l.setSerial(id);
            session.delete(l);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateLabels(Labels e) {
        try {
            Transaction transaction=session.beginTransaction();
            session.update(e);
            transaction.commit();
        } catch (Exception m) {
            m.printStackTrace();
        }
    }

    public int getKeyByAttribute(String attributeName, String attributeValue) {
        int key = 1;
        try {
            attributeName = String.valueOf(attributeName.charAt(0)).toUpperCase() + attributeName.substring(1);
            String query = "from Labels where " + attributeName + " = '" + attributeValue + "'";
            System.out.println(query);
            Labels l = (Labels)session.createQuery(query).uniqueResult();
            key = l.getSerial();
            System.out.println("result key is " + key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }

    public List<String> getAttributesByName(String attributeName) {
        attributeName = String.valueOf(attributeName.charAt(0)).toUpperCase() + attributeName.substring(1);
        List<String> attributes = new LinkedList<>();
        try {
            String query = "select " + attributeName + " from Labels";
            System.out.println(query);
            attributes = session.createQuery(query).list();
            return attributes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
