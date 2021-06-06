package database.dynamicDAOs;

import database.daos.LabelsDAO;
import database.standarizedTables.LabelObject;
import database.standarizedTables.StdLocation;
import org.hibernate.Transaction;
import java.util.*;


public class DynamicLocationDAO extends UserCommonAttributesDAO {

    public DynamicLocationDAO() {
        super();
    }

    @Override
    public List<LabelObject> getAllLabels() {
        List list = null;
        try {
            list = session.createQuery("from StdLocation").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void saveLabel(LabelObject labelObject) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            StdLocation l = new StdLocation(labelObject.getLabelId(), labelObject.getUserID());
            session.save(l);
            transaction.commit();
            session.close();
        } catch (Exception exp) {
            if (transaction != null) {
                transaction.rollback();
            }
            exp.printStackTrace();
        }
    }

    @Override
    public Boolean checkLabelByKey(int labelID, int userID) {
        try {
            StdLocation l = (StdLocation) session.createQuery("from StdLocation where Lid = "+ labelID + " and Uid = " + userID).uniqueResult();
            return l != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void deleteLabelByKey(int labelID, int userID) {
        StdLocation l = new StdLocation();
        try {
            Transaction transaction = session.beginTransaction();
            l.setLabelId(labelID);
            l.setUserId(userID);
            session.delete(l);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateLabel(LabelObject labelObject) {
        StdLocation l = new StdLocation(labelObject.getLabelId(), labelObject.getUserID());
        try {
            Transaction transaction=session.beginTransaction();
            session.update(l);
            transaction.commit();
        } catch (Exception m) {
            m.printStackTrace();
        }
    }

    @Override
    public List<LabelObject> getAllValuesWithUserID(int userID) {
        List list = null;
        try {
            list = session.createQuery("from StdLocation where Uid = "+ userID).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<LabelObject> getAllValuesWithLabelID(int labelID) {
        List list = null;
        try {
            list = session.createQuery("from StdLocation where Lid = "+ labelID).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<String> findCommon(int uid1, int uid2) {
        try {
            List list1 = session.createQuery("from StdLocation where Uid = "+ uid1).list();
            List list2 = session.createQuery("from StdLocation where Uid = "+ uid2).list();
            List<String> common = new LinkedList<>();
            Map<Integer, Integer> map1 = new HashMap<>(), map2 = new HashMap<>();
            for (Object each: list1) {
                LabelObject labelObject = (LabelObject) each;
                map1.put(labelObject.getLabelId(), labelObject.getUserID());
            }
            for (Object each: list2) {
                LabelObject labelObject = (LabelObject) each;
                map2.put(labelObject.getLabelId(), labelObject.getUserID());
            }
            Set<Integer> set1 = map1.keySet(), set2 = map2.keySet();
            for (int lid: set1) {
                if (set2.contains(lid)) common.add(LabelsDAO.getLabelsByKey(lid).getLocations());
            }
            return common;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

