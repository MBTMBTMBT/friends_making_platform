package database.dynamicDAOs;

import database.daos.LabelsDAO;
import database.standarizedTables.LabelObject;
import database.standarizedTables.StdBooks;
import database.standarizedTables.StdSports;
import org.hibernate.Transaction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class DynamicBooksDAO extends UserCommonAttributesDAO {

	public DynamicBooksDAO() {
		super();
	}

	@Override
	public List<LabelObject> getAllLabels() {
		List list = null;
		try {
			list = session.createQuery("from StdBooks").list();
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
			StdBooks l = new StdBooks(labelObject.getLabelId(), labelObject.getUserID());
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
			StdBooks l = (StdBooks) session.createQuery("from StdBooks where Bid = "+ labelID + " and Uid = " + userID).uniqueResult();
			return l != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void deleteLabelByKey(int labelID, int userID) {
		StdBooks l = new StdBooks();
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
		StdBooks l = new StdBooks(labelObject.getLabelId(), labelObject.getUserID());
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
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM books WHERE Uid = ?;");
			ps.setInt(1, userID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				int Sid = rs.getInt("Bid");
				int Uid = rs.getInt("Uid");
				StdBooks s = new StdBooks(Sid,Uid);
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
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM books WHERE Bid = ?;");
			ps.setInt(1, labelID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				int Sid = rs.getInt("Bid");
				int Uid = rs.getInt("Uid");
				StdBooks s = new StdBooks(Sid,Uid);
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
	public List<String> findCommon(int uid1, int uid2) {
		try {
			List list1 = session.createQuery("from StdBooks where Uid = "+ uid1).list();
			List list2 = session.createQuery("from StdBooks where Uid = "+ uid2).list();
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
				if (set2.contains(lid)) common.add(LabelsDAO.getLabelsByKey(lid).getBook());
			}
			return common;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}


