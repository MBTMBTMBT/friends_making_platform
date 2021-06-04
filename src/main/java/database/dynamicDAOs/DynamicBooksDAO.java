package database.dynamicDAOs;

import database.daos.LabelsDAO;
import database.standarizedTables.LabelObject;
import database.standarizedTables.StdBooks;
import database.supports.HibernateUtil;
import database.tables.Books;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
			StdBooks l = new StdBooks(labelObject.getLabelId(), labelObject.getUserId());
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
		StdBooks l = new StdBooks(labelObject.getLabelId(), labelObject.getUserId());
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
			list = session.createQuery("from StdBooks where Uid = "+ userID).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<LabelObject> getAllValuesWithLabelID(int labelID) {
		List list = null;
		try {
			list = session.createQuery("from StdBooks where Bid = "+ labelID).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
				map1.put(labelObject.getLabelId(), labelObject.getUserId());
			}
			for (Object each: list2) {
				LabelObject labelObject = (LabelObject) each;
				map2.put(labelObject.getLabelId(), labelObject.getUserId());
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


