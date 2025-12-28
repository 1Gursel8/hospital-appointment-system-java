package gurseluyanik;

public class Patient extends Person{
	
	// Define serialVersionUID for Patient class
	private static final long serialVersionUID = 1L;
	private Rendezvous rendezvous;

	// Define constructor for Patient class
	public Patient(String name, long national_id) {
		super(name, national_id);
	}
	
	// To use in Schedule class, we need setter for rendezvous
	public void setRendezvous(Rendezvous rendezvous) {
		this.rendezvous = rendezvous;
	}
	
	// Define getter for rendezvous, we need to print Patient's Rendezvous so we need to access it
	public Rendezvous getRendezvous() {
		return this.rendezvous;
	}
	
	// Define (Override) toString method for Patient class
	public String toString() {
		return "Patient " + super.toString();
	}
}
