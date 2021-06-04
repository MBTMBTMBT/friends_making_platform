package database.standarizedTables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="location")

public class StdLocation implements LabelObject {
	
	@Id
	@Column(name="Lid")
	private int Lid;
	
	@Column(name="Uid")
	private int Uid;
	
	public StdLocation() {
	}
	
	
	public StdLocation(int Lid, int Uid) {
		super();
		this.Lid = Lid;
		this.Uid = Uid;
	}

	
	public int getLid() {
		return Lid;
	}

	public void setLid(int Lid) {
		this.Lid = Lid;
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
		return Lid;
	}

	@Override
	public void setLabelId(int labelId) {
		this.Lid = labelId;
	}

	@Override
	public int getUserId() {
		return Uid;
	}

	@Override
	public void setUserId(int UserId) {
		this.Uid = UserId;
	}
}


