package database.tables;

import database.supports.JDBCTool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;


public class UserPerson {
	private int systemID;
	private int userID;
	private String emailAddress;
	private String wechat;
	private String genderOrientation;
	private Date dateOfBirth;
	private String slogan;
	private int work;
	private int mentorID;
	//private int SystemID;
	private String surname;
	private String forename;
	private String gender;
	private String screenName;
	private String headIcon;
	private String password;
	
	public UserPerson(){
	}
	
	public UserPerson(int SystemID, int UserID, String emailAddress, String Wechat, String GenderOrientation, Date dateOfBirth, String Slogan, int Work, int MentorID, String Surname, String Forename, String Gender, String ScreenName, String HeadIcon, String password) {
		super();
		this.systemID = SystemID;
		this.userID = UserID;
		this.emailAddress = emailAddress;
		this.wechat = Wechat;
		this.genderOrientation = GenderOrientation;
		this.dateOfBirth = dateOfBirth;
		this.slogan = Slogan;
		this.work = Work;
		this.mentorID = MentorID;
		this.surname = Surname;
		this.forename = Forename;
		this.gender = Gender;
		this.screenName = ScreenName;
		this.headIcon = HeadIcon;
		this.password = password;
	}
	
	public int getSystemID() {
		return systemID;
	}

	public void setSystemID(int SystemID) {
		this.systemID = SystemID;
	}

	public int getUserID() {
		return userID;
	}
	
	public void setUserID(int UserID) {
		this.userID = UserID;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String Emailaddress) {
		this.emailAddress = Emailaddress;
	}
	
	public String getWechat() {
		return wechat;
	}

	public void setWechat(String Wechat) {
		this.wechat = Wechat;
	}

	public String getGenderOrientation() {
		return genderOrientation;
	}

	public void setGenderOrientation(String GenderOrientation) {
		this.genderOrientation = GenderOrientation;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date DataOfBirth) {
		this.dateOfBirth = DataOfBirth;
	}

	public String getSlogan() {
		return slogan;
	}
	
	public void setSlogan(String Slogan) {
		this.slogan = Slogan;
	}
	
	public int getWork() {
		return work;
	}

	public void setWork(int Work) {
		this.work = Work;
	}
	
	public int getMentorID() {
		return mentorID;
	}

	public void setMentorID(int MentorID) {
		this.mentorID = MentorID;
	}
	
	public String getSurname() {
		return surname;
	}

	public void setSurname(String Surname) {
		this.surname = Surname;
	}

	public String getForename() {
		return forename;
	}

	public void setForename(String Forename) {
		this.forename = Forename;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String Gender) {
		this.gender = Gender;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String ScreenName) {
		this.screenName = ScreenName;
	}
	
	public String getHeadIcon() {
		return headIcon;
	}

	public void setHeadIcon(String HeadIcon) {
		this.headIcon = HeadIcon;
	}
	
	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}

	static public List<UserPerson> userPersonJoin() {
		List<UserPerson> list = new LinkedList<UserPerson>();
		
		try {
			Connection conn = JDBCTool.getConnection();
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery("SELECT * FROM user inner join person on user.systemid = person.systemid");
			
			while(rs.next()) {
				
				int systemID = rs.getInt("SystemID");
				int userID = rs.getInt("UserID");
				String emailAddress = rs.getString("Emailaddress");
				String wechat = rs.getString("Wechat");
				String genderOrientation = rs.getString("GenderOrientation");
				Date dataOfBirth = rs.getDate("DataOfBirth");
				String slogan = rs.getString("Slogan");
				int work = rs.getInt("Work");
				int mentorID = rs.getInt("MentorID");
				String surname = rs.getString("Surname");
				String forename = rs.getString("Forename");
				String gender = rs.getString("Gender");
				String screenName = rs.getString("ScreenName");
				String headIcon = rs.getString("HeadIcon");
				String password = rs.getString("password");
				
				UserPerson e = new UserPerson(systemID, userID, emailAddress, wechat, genderOrientation, dataOfBirth, slogan, work, mentorID, surname, forename, gender, screenName, headIcon, password);
				list.add(e);
			}
			rs.close();
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public String toString() {
		String output = this.systemID+"\t\t"+this.userID+"\t\t"+this.emailAddress;

		if(this.emailAddress == null ||this.emailAddress.length()<8) {
			output = output + "\t";
		}

		output = output+ "\t"+this.wechat;

		if(this.wechat == null || this.wechat.length()<8) {
			output = output + "\t";
		}

		output +="\t"+this.genderOrientation;

		if(this.genderOrientation == null || this.genderOrientation.length()<8) {
			output = output + "\t";
		}


		output +="\t"+this.dateOfBirth + "\t" + this.slogan;

		if(this.slogan == null || this.slogan.length()<8) {
			output = output + "\t";
		}

		output +="\t"+this.work+"\t\t"+this.mentorID+"\t\t";

		output += this.systemID+"\t\t"+this.surname;

		if(this.surname == null || this.surname.length()<8) {
			output = output + "\t";
		}

		output = output+ "\t"+this.forename;

		if(this.forename == null || this.forename.length()<8) {
			output = output + "\t";
		}

		output +="\t"+this.gender;

		if(this.gender == null || this.gender.length()<8) {
			output = output + "\t";
		}

		output +="\t"+this.screenName;

		if(this.screenName == null || this.screenName.length()<8) {
			output = output + "\t";
		}


		output +="\t"+this.headIcon;

		if(this.headIcon == null || this.headIcon.length()<8) {
			output = output + "\t";
		}


		output +="\t"+this.password;

		return output;
	}

	public static void main(String[] args) {
		List<UserPerson> list = UserPerson.userPersonJoin();
		for (UserPerson userPerson: list) {
			System.out.println(userPerson);
		}
	}
}

