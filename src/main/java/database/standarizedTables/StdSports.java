package database.standarizedTables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sports")

public class StdSports implements LabelObject {
	
	@Id
	@Column(name="Sid")
	private int Sid;
	
	@Column(name="Uid")
	private int Uid;
	
	public StdSports() {
	}
	
	
	public StdSports(int Sid, int Uid) {
		super();
		this.Sid = Sid;
		this.Uid = Uid;
	}

	
	public int getSid() {
		return Sid;
	}

	public void setSid(int Sid) {
		this.Sid = Sid;
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
		return Sid;
	}

	@Override
	public void setLabelId(int labelId) {
		this.Sid = labelId;
	}

	@Override
	public int getUserID() {
		return Uid;
	}

	@Override
	public void setUserId(int userId) {
		this.Uid = userId;
	}
}


