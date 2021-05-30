package database.tables;

public class Admin{
	
	private int id;
	private String firstname;
	private String familyname;
	private String password;
	private String gender;
	private int age;
	
	public Admin(int id, String firstname, String familyname,String password,String gender, int age) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.familyname = familyname;
		this.password = password;
		this.gender = gender;
		this.age = age;
	}

	
	
	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getFirstname() {
		return firstname;
	}




	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}




	public String getFamilyname() {
		return familyname;
	}




	public void setFamilyname(String familyname) {
		this.familyname = familyname;
	}

	
	public String password() {
		return password;
	}


	public void password(String password) {
		this.password = password;
	}
	
	
	
	public String getGender() {
		return gender;
	}

	
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	

	public int getAge() {
		return age;
	}




	public void setAge(int age) {
		this.age = age;
	}

	


	public String toString() {
		String output = this.id+"\t\t"+this.firstname;
		
		if(this.firstname == null || this.firstname.length()<8) {
			output = output + "\t";
		}
		
		output = output +"\t"+this.familyname;
		
		
		if(this.familyname == null || this.familyname.length()<8) {
			output = output + "\t";
		}
		
		output +="\t"+this.password;
		
		if(this.password == null || this.password.length()<8) {
			output = output + "\t";
		}
		
		output +="\t"+this.gender;
		
		if(this.gender == null || this.gender.length()<8) {
			output = output + "\t";
		}
		
		output +="\t"+this.age;
		
		
		return output;
	}
	

}

