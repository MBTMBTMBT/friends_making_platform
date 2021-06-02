package database.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.print.attribute.standard.DateTimeAtCompleted;

@Entity
@Table(name="join_event")


public class JoinEvent {
	
	@Id
	@Column(name="Uid")
	private int Uid;
	
	@Column(name="EventLocationID")
	private int EventLocationID;
	
	
	public JoinEvent() {
	}
	
	
	public JoinEvent(int Uid,int EventLocationID) {
		super();
		this.Uid = Uid;
		this.EventLocationID = EventLocationID;
	}


	
	
	public int getUid() {
		return Uid;
	}

	public void setUid(int Uid) {
		this.Uid = Uid;
	}
	
	public int getEventLocationID() {
		return EventLocationID;
	}

	public void setEventLocationID(int EventLocationID) {
		this.EventLocationID = EventLocationID;
	}



	public String toString() {
		String output = "\t\t";

		return output;
	}

}


