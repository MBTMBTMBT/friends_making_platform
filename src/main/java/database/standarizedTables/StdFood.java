package database.standarizedTables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="food")

public class StdFood implements LabelObject {
	
	@Id
	@Column(name="Fid")
	private int Fid;
	
	@Column(name="Uid")
	private int Uid;
	
	public StdFood() {
	}
	
	
	public StdFood(int Fid, int Uid) {
		super();
		this.Fid = Fid;
		this.Uid = Uid;
	}

	
	public int getFid() {
		return Fid;
	}

	public void setFid(int Fid) {
		this.Fid = Fid;
	}
	
	
	public int getUid() {
		return Uid;
	}

	public void setUid(int Uid) {
		this.Uid = Uid;
	}


	public String toString() {
		String output = "\t\t";

		return output;
	}

	@Override
	public int getLabelId() {
		return Fid;
	}

	@Override
	public void setLabelId(int labelId) {
		this.Fid = labelId;
	}

	@Override
	public int getUserID() {
		return Uid;
	}

	@Override
	public void setUserId(int UserId) {
		this.Uid = UserId;
	}
}


