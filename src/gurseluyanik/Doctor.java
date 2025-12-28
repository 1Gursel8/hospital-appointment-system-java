package gurseluyanik;

public class Doctor extends Person{
	
	// Define serialVersionUID for Doctor class
	private static final long serialVersionUID = 1L;

	// Define attributes of Doctor class, name and national_id came from Person class with inheritance
	private final int diploma_id;
	
	// To use getSchedule method, we need an attribute with Schedule
	private Schedule schedule;
	
	// We need a Section attribute for constructor
	private Section section;
	
	// Define constructor of Doctor class
	public Doctor (String name, long national_id, int diploma_id, int maxPatientPerDay, Section section) {
		
		// Calling constructor of Person class with super() method
		super(name, national_id);
		this.diploma_id = diploma_id;
		this.section = section;
		this.schedule = new Schedule(maxPatientPerDay); // To define a Schedule for Doctor, we need maxPatientPerDay for each Doctor
		this.schedule.setDoctor(this); // Set our instance (doctor)
	}
	
	// Define getter for schedule
	public Schedule getSchedule() {
		return this.schedule;
	}
	
	// Define getter for section
	public Section getSection() {
		return this.section;
	}
	
	// To use diploma_id in Section class, we need to define getter for diploma_id
	public int getDiplomaID() {
		return this.diploma_id;
	}
	
	// Define (Override) toString method for Doctor class, output:
	// Name: name
	// National ID: national_id
	// Diploma ID: diploma_id
	public String toString() {
		return "Doctor " + super.toString() + " Section: " + this.section;
	}
}
