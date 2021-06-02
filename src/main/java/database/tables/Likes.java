package database.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.print.attribute.standard.DateTimeAtCompleted;

@Entity
@Table(name="likes")

public class Likes {
	
	@Id
	@Column(name="Uid1")
	private int Uid1;
	
	@Column(name="Uid2")
	private int Uid2;
	
	@Column(name="Confession")
	private String Confession;
	
	public Likes() {
	}
	
	
	public Likes(int Uid1, int Uid2, String Confession) {
		super();

		this.Uid1 = Uid1;
		this.Uid2 = Uid2;
		this.Confession = Confession;
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
	
	public String getConfession() {
		return Confession;
	}

	public void setConfession(String Confession) {
		this.Confession = Confession;
	}


	public String toString() {
		String output = "\t\t";

		return output;
	}

}



