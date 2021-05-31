package database.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="phsycological_mentor ")

public class Phsycological_Mentor {
	@Id
	@Column(name="SystemID")
	private int SystemID;
	
	@Column(name="EmployeeID")
	private int EmployeeID;
	
	@Column(name="MentorNumber")
	private int MentorNumber;
	
	@Column(name="GenderOrientationInCharge")
	private String GenderOrientationInCharge;
	
	@Column(name="AgeRangeInRange")
	private int AgeRangeInRange;
	
	
	
	public Phsycological_Mentor () {
	}
	
	
	public Phsycological_Mentor (int SystemID, int EmployeeID , int MentorNumber, String GenderOrientationInCharge, int AgeRangeInRange) {
		super();

		this.SystemID = SystemID;
		this.EmployeeID = EmployeeID;
		this.MentorNumber = MentorNumber;
		this.GenderOrientationInCharge = GenderOrientationInCharge;
		this.AgeRangeInRange = AgeRangeInRange;
	}


	
	
	public int getSystemID() {
		return SystemID;
	}

	public void setSystemID(int SystemID) {
		this.SystemID = SystemID;
	}

	public int getEmployeeID() {
		return EmployeeID;
	}

	public void setEmployeeID(int EmployeeID) {
		this.EmployeeID = EmployeeID;
	}
	
	public int getMentorNumber() {
		return MentorNumber;
	}

	public void setMentorNumber(int MentorNumber) {
		this.MentorNumber = MentorNumber;
	}
	
	public String getGenderOrientationInCharge() {
		return GenderOrientationInCharge;
	}

	public void setGenderOrientationInCharge(String GenderOrientationInCharge) {
		this.GenderOrientationInCharge = GenderOrientationInCharge;
	}
	
	public int getAgeRangeInRange() {
		return AgeRangeInRange;
	}

	public void setAgeRangeInRange(int AgeRangeInRange) {
		this.AgeRangeInRange = AgeRangeInRange;
	}
	


	public String toString() {
		String output = this.SystemID+"\t\t"+this.EmployeeID;

		return output;
	}

}
