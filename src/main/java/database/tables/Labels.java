package database.tables;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.print.attribute.standard.DateTimeAtCompleted;

@Entity
@Table(name="labels")

public class Labels {
	
	@Id
	@Column(name="Serial")
	private int Serial;
	
	@Column(name="Locations")
	private String Locations;
	
	@Column(name="Work")
	private String Work;
	
	@Column(name="Food")
	private String Food;
	
	@Column(name="Film")
	private String Film;
	
	@Column(name="Book")
	private String Book;
	
	@Column(name="Sport")
	private String Sport;
	
	@Column(name="Activity")
	private String Activity;
	
	
	public Labels() {
	}
	
	
	public Labels(int Serial,String Locations,String Work,String Food,String Film,String Book,String Sport,String Activity) {
		super();
		this.Serial = Serial;
		this.Locations = Locations;
		this.Work = Work;
		this.Food = Food;
		this.Film = Film;
		this.Book = Book;
		this.Sport = Sport;
		this.Activity = Activity;
	}


	
	
	public int getSerial() {
		return Serial;
	}

	public void setSerial(int Serial) {
		this.Serial = Serial;
	}
	
	
	public String getLocations() {
		return Locations;
	}

	public void setLocations(String Locations) {
		this.Locations = Locations;
	}
	
	
	
	public String getWork() {
		return Work;
	}

	public void setWork(String Work) {
		this.Work = Work;
	}

	
	public String getFood() {
		return Food;
	}

	public void setFood(String Food) {
		this.Food = Food;
	}
	
	
	public String getFilm() {
		return Film;
	}

	public void setFilm(String Film) {
		this.Film = Film;
	}
	
	
	
	public String getBook() {
		return Book;
	}

	public void setBook(String Book) {
		this.Book = Book;
	}
	
	
	
	public String getSport() {
		return Sport;
	}

	public void setSport(String Sport) {
		this.Sport = Sport;
	}
	
	
	public String getActivity() {
		return Activity;
	}

	public void setActivity(String Activity) {
		this.Activity = Activity;
	}
	
	



	public String toString() {
		String output = "\t\t";

		return output;
	}

}


