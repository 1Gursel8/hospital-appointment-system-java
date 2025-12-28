package gurseluyanik;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Rendezvous implements Serializable{
	
	// Define attributes of Rendezvous class and serialVersionUID
	private Date dateTime;
	private Doctor doctor;
	private Patient patient;
	private static final long serialVersionUID = 1L;
	
	
	// Define constructor of Rendezvous class
	public Rendezvous(Date dateTime, Doctor doctor, Patient patient) {
		this.dateTime = dateTime;
		this.doctor = doctor;
		this.patient = patient;
	}
	
	// To check date in Schedule, we need a getter for dateTime
	public Date getDateTime() {
		return this.dateTime;
	}
	
	// Define getter for patient
	public Patient getPatient() {
		return this.patient;
	}
	
	// Define getter for doctor
	public Doctor getDoctor() {
		return this.doctor;
	}
	
	// Define stringDate method
	public String stringDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(this.dateTime);
	}
	
	// Define (Override) toString method. We use this method in Schedule's toString method
	public String toString() {
		// Define a date formatter for converting date to String and adding this to message
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return "Date: " + formatter.format(this.dateTime) + " Doctor "+ doctor.toString();
	}
}
