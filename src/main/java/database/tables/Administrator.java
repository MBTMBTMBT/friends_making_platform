package database.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="administrator")
public class Administrator {
	
	@Id
	@Column(name="SystemID")
	private int SystemID;
	
	@Column(name="EmployeeID")
	private int EmployeeID;
	
	@Column(name="AdminNumber")
	private int AdminNumber;
	
	public Administrator() {
	}
	
	
	public Administrator(int SystemID, int EmployeeID, int AdminNumber) {
		super();

		this.SystemID = SystemID;
		this.EmployeeID = EmployeeID;
		this.AdminNumber = AdminNumber;
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
	
	
	public int getAdminNumber() {
		return AdminNumber;
	}

	public void setAdminNumber(int AdminNumber) {
		this.AdminNumber = AdminNumber;
	}



	public String toString() {
		String output = this.SystemID+"\t\t"+this.EmployeeID+"\t\t"+this.AdminNumber;

		return output;
	}

}
