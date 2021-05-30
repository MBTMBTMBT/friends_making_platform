package database.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="person")
public class Person {
	
	@Id
	@Column(name="SystemID")
	private int SystemID;
	
	@Column(name="Surname")
	private String Surname;
	
	@Column(name="Forename")
	private String Forename;
	
	@Column(name="Gender")
	private String Gender;
	
	@Column(name="ScreenName")
	private String ScreenName;
	
	@Column(name="HeadIcon")
	private String HeadIcon;
	
	@Column(name="password")
	private String password;
	
	public Person() {
	}
	
	
	public Person(int SystemID, String Surname, String Forename, String Gender, String ScreenName, String HeadIcon, String password) {
		super();

		this.SystemID = SystemID;
		this.Surname = Surname;
		this.Forename = Forename;
		this.Gender = Gender;
		this.ScreenName = ScreenName;
		this.HeadIcon = HeadIcon;
		this.password = password;
	}


	
	
	public int getSystemID() {
		return SystemID;
	}




	public void setSystemID(int SystemID) {
		this.SystemID = SystemID;
	}




	public String getSurname() {
		return Surname;
	}




	public void setSurname(String Surname) {
		this.Surname = Surname;
	}




	public String getForename() {
		return Forename;
	}




	public void setForename(String Forename) {
		this.Forename = Forename;
	}




	public String getGender() {
		return Gender;
	}




	public void setGender(String Gender) {
		this.Gender = Gender;
	}



	public String getScreenName() {
		return ScreenName;
	}




	public void setScreenName(String ScreenName) {
		this.ScreenName = ScreenName;
	}
	
	
	public String getHeadIcon() {
		return HeadIcon;
	}




	public void setHeadIcon(String HeadIcon) {
		this.HeadIcon = HeadIcon;
	}
	
	
	
	public String getpassword() {
		return password;
	}




	public void setpassword(String password) {
		this.password = password;
	}



	public String toString() {
		String output = this.SystemID+"\t\t"+this.Surname;
		
		if(this.Surname == null || this.Surname.length()<8) {
			output = output + "\t";
		} 
		
		output = output+ "\t"+this.Forename;
		
		if(this.Forename == null || this.Forename.length()<8) {
			output = output + "\t";
		}
		
		output +="\t"+this.Gender;
		
		if(this.Gender.length()<8) {
			output = output + "\t";
		}
		
		output +="\t"+this.ScreenName;
		
		if(this.ScreenName.length()<8) {
			output = output + "\t";
		}


		output +="\t"+this.HeadIcon;
		
		if(this.HeadIcon.length()<8) {
			output = output + "\t";
		}
		
		
		output +="\t"+this.password;

		
		return output;
	}
	

}
