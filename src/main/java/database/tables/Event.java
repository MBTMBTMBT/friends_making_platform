package database.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.print.attribute.standard.DateTimeAtCompleted;

@Entity
@Table(name="event")

public class Event {
	
	@Id
	@Column(name="LocationID")
	private int LocationID;
	
	@Column(name="Time")
	private String Time;
	
	@Column(name="Number_of_participants")
	private int Numberofparticipants;
	
	@Column(name="Activities")
	private String Activities;
	
	public Event() {
	}
	
	
	public Event(int LocationID, String Time, int Numberofparticipants,String Activities) {
		super();

		this.LocationID = LocationID;
		this.Time = Time;
		this.Numberofparticipants = Numberofparticipants;
		this.Activities = Activities;
	}


	
	
	public int getLocationID() {
		return LocationID;
	}

	public void setLocationID(int LocationID) {
		this.LocationID = LocationID;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String Time) {
		this.Time = Time;
	}
	
	public int getNumberofparticipants() {
		return Numberofparticipants;
	}

	public void setNumberofparticipants(int Numberofparticipants) {
		this.Numberofparticipants = Numberofparticipants;
	}
	
	public String getActivities() {
		return Activities;
	}

	public void setActivities(String Activities) {
		this.Activities = Activities;
	}



	public String toString() {
		String output = "\t\t";

		return output;
	}

}


