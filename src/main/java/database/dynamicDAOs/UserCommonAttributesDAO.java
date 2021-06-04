package database.dynamicDAOs;

import database.standarizedTables.LabelObject;

import java.util.List;

public abstract class UserCommonAttributesDAO extends DynamicDAO {
    public abstract List<LabelObject> getAllLabels();
    public abstract void saveLabel(LabelObject labelObject);
    public abstract Boolean checkLabelByKey(int labelID, int userID);
    public abstract void deleteLabelByKey(int labelID, int userID);
    public abstract void updateLabel(LabelObject labelObject);
    public abstract List<LabelObject> getAllValuesWithUserID(int userID);
    public abstract List<LabelObject> getAllValuesWithLabelID(int labelID);
    public abstract List<String> findCommon(int uid1, int uid2);
}
