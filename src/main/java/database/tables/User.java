package database.tables;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@Column(name="SystemID")
	private int SystemID;
	
	@Column(name="UserID")
	private int UserID;
	
	@Column(name="Emailaddress")
	private String Emailaddress;
	
	@Column(name="Wechat")
	private String Wechat;
	
	@Column(name="GenderOrientation")
	private String GenderOrientation;
	
	@Column(name="DataOfBirth")
	private Date DataOfBirth;

	
	@Column(name="Slogan")
	private String Slogan;
	
	@Column(name="Work")
	private int Work;
	
	@Column(name="MentorID")
	private int MentorID;
	
	public User() {
	}
	
	
	public User(int SystemID, int UserID, String Emailaddress, String Wechat, String GenderOrientation, Date DataOfBirth, String Slogan,int Work,int MentorID ) {
		super();

		this.SystemID = SystemID;
		this.UserID = UserID;
		this.Emailaddress = Emailaddress;
		this.Wechat = Wechat;
		this.GenderOrientation = GenderOrientation;
		this.DataOfBirth = DataOfBirth;
		this.Slogan = Slogan;
		this.Work = Work;
		this.MentorID = MentorID;
	}


	
	
	public int getSystemID() {
		return SystemID;
	}


	public void setSystemID(int SystemID) {
		this.SystemID = SystemID;
	}
	

	public int getUserID() {
		return UserID;
	}
	
	public void setUserID(int UserID) {
		this.UserID = UserID;
	}


	public String getEmailaddress() {
		return Emailaddress;
	}


	public void setEmailaddress(String Emailaddress) {
		this.Emailaddress = Emailaddress;
	}

	
	public String getWechat() {
		return Wechat;
	}


	public void setWechat(String Wechat) {
		this.Wechat = Wechat;
	}



	public String getGenderOrientation() {
		return GenderOrientation;
	}


	public void setGenderOrientation(String GenderOrientation) {
		this.GenderOrientation = GenderOrientation;
	}



	public Date getDataOfBirth() {
		return DataOfBirth;
	}


	public void setDataOfBirth(Date DataOfBirth) {
		this.DataOfBirth = DataOfBirth;
	}



	public String getSlogan() {
		return Slogan;
	}


	public void setSlogan(String Slogan) {
		this.Slogan = Slogan;
	}
	
	
	
	public int getWork() {
		return Work;
	}

	public void setWork(int Work) {
		this.Work = Work;
	}
	
	
	public int getMentorID() {
		return MentorID;
	}

	public void setMentorID(int MentorID) {
		this.MentorID = MentorID;
	}
	
	


	public String toString() {
		String output = this.SystemID+"\t\t"+this.UserID+"\t\t"+this.Emailaddress;
		
		if(this.Emailaddress.length()<8) {
			output = output + "\t";
		} 
		
		output = output+ "\t"+this.Wechat;
		
		if(this.Wechat == null || this.Wechat.length()<8) {
			output = output + "\t";
		}
		
		output +="\t"+this.GenderOrientation;
		
		if(this.GenderOrientation == null || this.GenderOrientation.length()<8) {
			output = output + "\t";
		}
		
		
		output +="\t"+this.DataOfBirth + "\t" + this.Slogan;	

		if(this.Slogan == null || this.Slogan.length()<8) {
			output = output + "\t";
		}
		
		output +="\t"+this.Work+"\t\t"+this.MentorID;
		
		return output;
	}

}
