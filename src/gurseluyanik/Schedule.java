package gurseluyanik;

import java.io.Serializable;
import java.util.Date;
import java.util.Calendar;
import java.util.LinkedList;

public class Schedule implements Serializable{
	
	// Define attributes of Schedule class and serialVersionUID
	private static final long serialVersionUID = 1L;
	private LinkedList<Rendezvous> sessions;
	private int maxPatientPerDay;
	private Doctor doctor;
	
	// Define constructor of Schedule class
	public Schedule (int maxPatientPerDay) {
		this.maxPatientPerDay = maxPatientPerDay;
		
		// Create LinkedList for sessions with new LinkedList<>()
		this.sessions = new LinkedList<Rendezvous>();
	}
	
	// Define getter for sessions
	public LinkedList<Rendezvous> getSessions(){
		return this.sessions;
	}
	// Define setter for Doctor
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	// Define getter for Doctor
	public Doctor getDoctor() {
		return this.doctor;
	}
	
	// Add new Rendezvous for Doctor's sessions
	public boolean addRendezvous(Patient p, Date desire) {
		int numberOfPatients = 0; // We need a variable to compared with maxPatientPerDay
		Calendar CDesire = Calendar.getInstance(); // Convert Date type instance to Calendar type instance for desire Date
		CDesire.setTime(desire); // Set Calendar from instance of Date for desire
		Calendar CSession = Calendar.getInstance(); // Convert Date type instance to Calendar type instance for session Date
		for (Rendezvous session: sessions) {
			CSession.setTime(session.getDateTime()); // Set Calendar from instance of Date for session
			// Checking that if desire and session are same year and same day of year. if all of them true, sessions contains desire
			if((CDesire.get(Calendar.YEAR) == CSession.get(Calendar.YEAR)) && 
					(CDesire.get(Calendar.DAY_OF_YEAR) == CSession.get(Calendar.DAY_OF_YEAR))) {
				numberOfPatients += 1;
			}
		}
		// if numberOfPatients is between 0 and maxPatientPerDay, we can add that Rendezvous to sessions
		if (numberOfPatients < maxPatientPerDay) {
			this.sessions.add(new Rendezvous(desire, this.doctor, p)); // Add new Rendezvous to sessions, this will be last element in LinkedList
			p.setRendezvous(this.sessions.getLast()); // Set Rendezvous for patient
			this.sessions.sort((a, b) -> { return 1 * a.getDateTime().compareTo(b.getDateTime()); } ); // Sort sessions by date
			return true;
		}
		else {
			return false;
		}
	}
	
	// Define (Override) toString method. We use it when we need to print Schedule's Patients and dates.
	public String toString() {
		String message = "";
		for(Rendezvous rendezvous: sessions) {
			message += rendezvous.toString() + "\n";
		}
		return message;
	}
}
