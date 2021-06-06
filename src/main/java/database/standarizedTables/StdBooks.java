package database.standarizedTables;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="books")

public class StdBooks implements LabelObject {
	
	@Id
	@Column(name="Bid")
	private int Bid;
	
	@Column(name="Uid")
	private int Uid;
	
	public StdBooks() {
	}
	
	
	public StdBooks(int Bid, int Uid) {
		super();
		this.Bid = Bid;
		this.Uid = Uid;
	}

	
	public int getBid() {
		return Bid;
	}

	public void setBid(int Bid) {
		this.Bid = Bid;
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
		return Bid;
	}

	@Override
	public void setLabelId(int labelId) {
		this.Bid = labelId;
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


