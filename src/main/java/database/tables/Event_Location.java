package database.tables;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.print.attribute.standard.DateTimeAtCompleted;

@Entity
@Table(name="event_location")

public class Event_Location {

	
	@Id
	@Column(name="LocationID")
	private int LocationID;
	
	@Column(name="LocationType")
	private String LocationType;
	
	@Column(name="GeographicalLocation")
	private String GeographicalLocation;
	
	@Column(name="ManagerID")
	private int ManagerID;
	
	@Column(name="StartTime")
	private Date StartTime;
	
	public Event_Location() {
	}
	
	
	public Event_Location(int LocationID, String LocationType, String GeographicalLocation, int ManagerID, Date StartTime) {
		super();

		this.LocationID = LocationID;
		this.LocationType = LocationType;
		this.GeographicalLocation = GeographicalLocation;
		this.ManagerID = ManagerID;
		this.StartTime = StartTime;
	}


	
	
	public int getLocationID() {
		return LocationID;
	}

	public void setLocationID(int LocationID) {
		this.LocationID = LocationID;
	}

	public String getLocationType() {
		return LocationType;
	}

	public void setLocationType(String LocationType) {
		this.LocationType = LocationType;
	}
	
	public String getGeographicalLocation() {
		return GeographicalLocation;
	}

	public void setGeographicalLocation(String GeographicalLocation) {
		this.GeographicalLocation = GeographicalLocation;
	}
	
	public int getManagerID() {
		return ManagerID;
	}

	public void setManagerID(int ManagerID) {
		this.ManagerID = ManagerID;
	}

	
	public Date getStartTime() {
		return StartTime;
	}

	public void setStartTime(Date StartTime) {
		this.StartTime = StartTime;
	}



	public String toString() {
		String output = "\t\t";

		return output;
	}

}


