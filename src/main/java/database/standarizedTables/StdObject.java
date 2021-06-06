package database.standarizedTables;

public class StdObject implements LabelObject {
    private int lid;
    private int uid;

    @Override
    public int getLabelId() {
        return lid;
    }

    @Override
    public void setLabelId(int labelId) {
        lid = labelId;
    }

    @Override
    public int getUserID() {
        return uid;
    }

    @Override
    public void setUserId(int userId) {
        uid = userId;
    }
}
