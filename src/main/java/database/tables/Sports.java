package database.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.print.attribute.standard.DateTimeAtCompleted;

@Entity
@Table(name="sports")

public class Sports {
	
	@Id
	@Column(name="Sid")
	private int Sid;
	
	@Column(name="Uid")
	private int Uid;
	
	public Sports() {
	}
	
	
	public Sports(int Sid, int Uid) {
		super();
		this.Sid = Sid;
		this.Uid = Uid;
	}

	
	public int getSid() {
		return Sid;
	}

	public void setSid(int Sid) {
		this.Sid = Sid;
	}
	
	
	public int getUid() {
		return Uid;
	}

	public void setUid(int Uid) {
		this.Uid = Uid;
	}


	public String toString() {
		String output = "\t\t";

		return output;
	}

}


