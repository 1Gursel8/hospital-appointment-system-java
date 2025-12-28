package gurseluyanik;

import java.io.Serializable;
import java.util.LinkedList;

public class Section implements Serializable{

	// Define attributes of Section class and serialVersionUID
	private static final long serialVersionUID = 1L;
	private final int id;
	private String name;
	private Hospital hospital;
	private LinkedList<Doctor> doctors;
	
	// Define constructor for Section class
	public Section (int sectionID, String name, Hospital hospital) {
		this.id = sectionID;
		this.name = name;
		this.hospital = hospital;
		doctors = new LinkedList<Doctor>();
	}
	
	// To use in Hospital class, we need getter for id
	public int getID() {
		return this.id;
	}
	
	// To use in Hospital class, we need getter for name
	public String getName() {
		return this.name;
	}
	
	// Define getter for hospital
	public Hospital getHospital() {
		return this.hospital;
	}
	
	// Define getter for doctors
	public LinkedList<Doctor> getDoctors(){
		return this.doctors;
	}
	
	// To list all Doctors in Section, we define listDoctors method
	public void listDoctors() {
		System.out.println("All doctors in " + this.name + ":\n");
		for(Doctor doctor: doctors) {
			System.out.println(doctor + "\n");
		}
	}

	// To get a doctor with id we want, we define getDoctor method
	public Doctor getDoctor(int diploma_id) {
		for (Doctor doctor: doctors) {
			if(doctor.getDiplomaID()==diploma_id) {
				return doctor;
			}
		}
		return null; // if there is no Doctor with that diploma_id, we will return null
	}
	
	// To add a Doctor to Section, we define addDoctor method
	public void addDoctor(Doctor doctor) {
		
		// if getDoctor method's output is null, then our Doctor isn't in doctors
		if(getDoctor(doctor.getDiplomaID()) == null) {
			this.doctors.add(doctor);
			this.doctors.sort((a, b) -> { return 1 * a.getName().compareTo(b.getName()); } ); // Sort doctors by name
		}
		else {
			throw new DuplicateInfoException("There is a doctor has same diploma ID with doctor that you try to add.");
		}
	}
	
	// Define (Override) toString method for Section class
	public String toString() {
		return this.name + " | " + this.hospital;
	}
}
