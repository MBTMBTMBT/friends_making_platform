package database.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.print.attribute.standard.DateTimeAtCompleted;

@Entity
@Table(name="relationship")

public class Relationship {
	
	@Id
	@Column(name="Uid1")
	private int Uid1;
	
	@Column(name="Uid2")
	private int Uid2;
	
	@Column(name="RelationState")
	private String RelationState;
	
	public Relationship() {
	}
	
	
	public Relationship(int Uid1, int Uid2, String RelationState) {
		super();

		this.Uid1 = Uid1;
		this.Uid2 = Uid2;
		this.RelationState = RelationState;
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
	
	public String getRelationState() {
		return RelationState;
	}

	public void setRelationState(String RelationState) {
		this.RelationState= RelationState;
	}


	public String toString() {
		String output = "\t\t";

		return output;
	}

}



