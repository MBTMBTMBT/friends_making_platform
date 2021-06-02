package database.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.print.attribute.standard.DateTimeAtCompleted;

@Entity
@Table(name="films")

public class Films {
	
	@Id
	@Column(name="Fid")
	private int Fid;
	
	@Column(name="Uid")
	private int Uid;
	
	public Films() {
	}
	
	
	public Films(int Fid, int Uid) {
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

}


