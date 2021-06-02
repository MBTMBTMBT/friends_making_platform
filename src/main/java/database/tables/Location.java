package database.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.print.attribute.standard.DateTimeAtCompleted;

@Entity
@Table(name="location")

public class Location {
	
	@Id
	@Column(name="Lid")
	private int Lid;
	
	@Column(name="Uid")
	private int Uid;
	
	public Location() {
	}
	
	
	public Location(int Lid, int Uid) {
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

}


