package database.dynamicDAOs;

import database.daos.LabelsDAO;
import database.standarizedTables.LabelObject;
import database.standarizedTables.StdSports;
import org.hibernate.Transaction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class DynamicSportsDAO extends UserCommonAttributesDAO {

    public DynamicSportsDAO() {
        super();
    }

    @Override
    public List<LabelObject> getAllLabels() {
        List list = null;
        try {
            list = session.createQuery("from StdSports").list();
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
            StdSports l = new StdSports(labelObject.getLabelId(), labelObject.getUserID());
            session.save(l);
            transaction.commit();
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
            StdSports l = (StdSports) session.createQuery("from StdSports where Sid = "+ labelID + " and Uid = " + userID).uniqueResult();
            return l != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void deleteLabelByKey(int labelID, int userID) {
        StdSports l = new StdSports();
        try {
            String sql = "DELETE FROM sports WHERE Sid = " + labelID + " and Uid = " + userID + ";";
            System.out.println(sql);
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.executeUpdate();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateLabel(LabelObject labelObject) {
        StdSports l = new StdSports(labelObject.getLabelId(), labelObject.getUserID());
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
        List<LabelObject> list = new LinkedList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM sports WHERE Uid = ?;");
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int Sid = rs.getInt("Sid");
                int Uid = rs.getInt("Uid");
                StdSports s = new StdSports(Sid,Uid);
                list.add(s);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            list = null;
        }
        // for (LabelObject each: list) System.out.println((each.getLabelId()));
        return list;
    }

    @Override
    public List<LabelObject> getAllValuesWithLabelID(int labelID) {
        List<LabelObject> list = new LinkedList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM sports WHERE Sid = ?;");
            ps.setInt(1, labelID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int Sid = rs.getInt("Sid");
                int Uid = rs.getInt("Uid");
                StdSports s = new StdSports(Sid,Uid);
                list.add(s);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            list = null;
        }
        for (LabelObject each: list) System.out.println((each.getUserID()));
        return list;
    }

    @Override
    public List<String> findCommon(int uid1, int uid2) {
        try {
            List list1 = session.createQuery("from StdSports where Uid = "+ uid1).list();
            List list2 = session.createQuery("from StdSports where Uid = "+ uid2).list();
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
                if (set2.contains(lid)) common.add(LabelsDAO.getLabelsByKey(lid).getSport());
            }
            return common;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}


