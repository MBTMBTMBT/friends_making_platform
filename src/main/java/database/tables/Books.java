package database.tables;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.print.attribute.standard.DateTimeAtCompleted;

@Entity
@Table(name="books")

public class Books {
	
	@Id
	@Column(name="Bid")
	private int Bid;
	
	@Column(name="Uid")
	private int Uid;
	
	public Books() {
	}
	
	
	public Books(int Bid, int Uid) {
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

}


