package database.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.print.attribute.standard.DateTimeAtCompleted;

@Entity
@Table(name="date")

public class Date {
	
	@Id
	@Column(name="Uid1")
	private int Uid1;
	
	@Column(name="Uid2")
	private int Uid2;
	
	@Column(name="HHDDMMYY")
	private String HHDDMMYY;
	
	@Column(name="GeographicalLocation")
	private String GeographicalLocation;
	
	public Date() {
	}
	
	
	public Date(int Uid1, int Uid2, String HHDDMMYY,String GeographicalLocation) {
		super();

		this.Uid1 = Uid1;
		this.Uid2 = Uid2;
		this.HHDDMMYY = HHDDMMYY;
		this.GeographicalLocation = GeographicalLocation;
	}


	
	
	public int getUid1() {
		return Uid1;
	}

	public void setUid1(int Uid1) {
		this.Uid1 = Uid1;
	}

	public int getUid2() {
		return Uid2;
	}

	public void setUid2(int Uid2) {
		this.Uid2 = Uid2;
	}
	
	public String getHHDDMMYY() {
		return HHDDMMYY;
	}

	public void setHHDDMMYY(String HHDDMMYY) {
		this.HHDDMMYY = HHDDMMYY;
	}
	
	public String getGeographicalLocation() {
		return GeographicalLocation;
	}

	public void setGeographicalLocation(String GeographicalLocation) {
		this.GeographicalLocation = GeographicalLocation;
	}



	public String toString() {
		String output = "\t\t";

		return output;
	}

}


