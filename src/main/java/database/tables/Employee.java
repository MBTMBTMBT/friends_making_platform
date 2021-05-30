package database.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee")

public class Employee {
	
	@Id
	@Column(name="SystemID")
	private int SystemID;
	
	@Column(name="EmployeeID")
	private int EmployeeID;
	
	public Employee() {
	}
	
	
	public Employee(int SystemID, int EmployeeID) {
		super();

		this.SystemID = SystemID;
		this.EmployeeID = EmployeeID;
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
	



	public String toString() {
		String output = this.SystemID+"\t\t"+this.EmployeeID;

		return output;
	}

}
