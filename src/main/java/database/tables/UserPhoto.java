package database.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.print.attribute.standard.DateTimeAtCompleted;

@Entity
@Table(name="user_photos")

public class UserPhoto {
	
	@Id
	@Column(name="Uid")
	private int Uid;
	
	@Column(name="PhotoPath(in the system)")
	private String PhotoPath;
	
	
	public UserPhoto() {
	}
	
	
	public UserPhoto(int Uid, String PhotoPath) {
		super();

		this.Uid = Uid;
		this.PhotoPath = PhotoPath;
	}


	
	
	public int getUid() {
		return Uid;
	}

	public void setUid(int Uid) {
		this.Uid = Uid;
	}
	
	
	public String getPootoPath() {
		return PhotoPath;
	}

	public void setPootoPath(String PootoPath) {
		this.PhotoPath = PootoPath;
	}


	public String toString() {
		String output = "\t\t";

		return output;
	}

}


