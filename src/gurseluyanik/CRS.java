package gurseluyanik;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

public class CRS implements Serializable{
	
	// Define attributes for CRS Class and serialVersionUID
	private static final long serialVersionUID = 1L;
	private HashMap<Long, Patient> patients;
	private LinkedList<Rendezvous> rendezvous;
	private HashMap<Integer, Hospital> hospitals;
	
	// Counters for patientID and hospitalID
	private static long idCounterPatient = 0;
	private static int idCounterHospital = 1;
	private static int idCounterSection = 0;
	
	// Define constructor for CRS Class
	public CRS() {
		patients = new HashMap<Long, Patient>();
		rendezvous = new LinkedList<Rendezvous>();
		hospitals = new HashMap<Integer, Hospital>();
	}
	
	// Define getter for rendezvous
	public LinkedList<Rendezvous> getRendezvous(){
		return this.rendezvous;
	}
	
	// Define getter for patients
	public HashMap<Long, Patient> getPatients(){
		return this.patients;
	}
	
	// Define getter for hospitals
	public HashMap<Integer, Hospital> getHospitals(){
		return this.hospitals;
	}
	// Define getPatientID method
	public long getPatientID(Patient patient) {
		for(long i = 1L; i <= (this.patients.size()); i++) {
			if(this.patients.get(i) == patient) {
				return i;
			}
		}
		return 0;
	}
	
	// To add a Patient, we need addPatient method
	public boolean addPatient(String name, long national_id) {
		this.patients.put(++idCounterPatient, new Patient(name, national_id));
		return true;
	}
	
	// To add a Rendezvous, we need addRendezvous method
	public boolean addRendezvous(Rendezvous r) {
		for (Rendezvous re: rendezvous) {
			if(re == r) {
				throw new DuplicateInfoException("The rendezvous you try to add already has added.");
			}
		}
		this.rendezvous.add(r);
		this.rendezvous.sort((a, b) -> { return 1 * a.getDateTime().compareTo(b.getDateTime()); } ); // Sort rendezvous by date
		return true;
	}
	
	// To add a Hospital, we need addHospital method
	public boolean addHospital(String name) {
		this.hospitals.put(idCounterHospital, new Hospital(idCounterHospital, name));
		idCounterHospital += 1;
		return true;
	}
	
	// Define addSectionToHospital method
	public void addSectionToHospital(String name, int hospitalID) {
		this.hospitals.get(hospitalID).addSection(new Section(++idCounterSection ,name, hospitals.get(hospitalID)));
	}
	
	// Define addDoctorToSection method
	public void addDoctorToSection(String name, long national_id, int diploma_id, int maxPatientPerDay, int hospitalID, int sectionID) {
		this.hospitals.get(hospitalID).getSection(sectionID).addDoctor(new Doctor(name, national_id, diploma_id, maxPatientPerDay, 
				hospitals.get(hospitalID).getSection(sectionID)));
	}
	
	// Define makeRandezvous method
	public boolean makeRandezvous(long patientID, int hospitalID, int sectionID, int diplomaID, Date desiredDate) {
		
		// Check if we can add this rendezvous
		// is there patient
		if(!this.patients.containsKey(patientID)) {
			throw new IDException("Not Found Patient ID");
		}
		
		// is there hospital
		if(!this.hospitals.containsKey(hospitalID)) {
			throw new IDException("Not Found Hospital ID");
		}
		// in hospital, is there that sectionID
		if(this.hospitals.get(hospitalID).getSection(sectionID) == null) {
			throw new IDException("Not Found Section ID");
		}
		
		// in section, is there doctor that has that diplomaID
		if(this.hospitals.get(hospitalID).getSection(sectionID).getDoctor(diplomaID) == null) {
			throw new IDException("Not Found Doctor Diploma ID");
		}
		
		// Try to add rendezvous to doctor's schedule and add that rendezvous to CRS. Then return boolean value
		if (this.hospitals.get(hospitalID).getSection(sectionID).getDoctor(diplomaID).
				getSchedule().addRendezvous(this.patients.get(patientID), desiredDate)) {
			addRendezvous(this.patients.get(patientID).getRendezvous());
			return true;
		}
		else
			return false;
	}
	
	// Define saveTableToDisk method
	 public void saveTablesToDisk(String fullPath) throws IOException {
		 // Control if is there a path
	     File directory = new File(fullPath).getParentFile();
	     if (directory != null && !directory.exists()) {
	    	 if (!directory.mkdirs()) {
	    		 throw new IOException("Failed to create directory: " + directory.getPath());
	    	 }
	     }
	     // if there is a path, then save files
	     try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fullPath))) {
	    	 oos.writeObject(this);
	     } catch (IOException e) {
	    	 System.err.println("A error occurred when save file: " + e.getMessage());
	         throw e;
	     }
	 }
	// Define loadTablesFromDisk method
	public void loadTablesFromDisk(String fullPath) throws IOException, ClassNotFoundException {
		File file = new File(fullPath);
		// Control if does file exits
	    if (!file.exists()) {
	    	throw new IOException("File can't be found: " + fullPath);
	    }
	    // Control if can file be read
	    if (!file.canRead()) {
	    	throw new IOException("File can't be read: " + fullPath);
	    }
	    // Get data from file
	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
	            CRS loadedCRS = (CRS)ois.readObject();
	            this.hospitals = loadedCRS.hospitals;
	            this.patients = loadedCRS.patients;
	            this.rendezvous = loadedCRS.rendezvous;
	            // To add sections correctly, we need update idCounters
	            idCounterSection = 0;
	            for(Hospital hospital: this.hospitals.values()) {
	            	for(Section section : hospital.getSections()) {
	            		idCounterSection += 1;
	            	}
	            }
	            idCounterPatient = this.patients.size();
	            idCounterHospital = this.hospitals.size() + 1;
	    } catch (InvalidClassException e) {
	    	System.err.println("Series version incompatibility: " + e.getMessage());
	        throw e;
	    } catch (IOException | ClassNotFoundException e) {
	        System.err.println("A error occurred when load file: " + e.getMessage());
	        throw e;
	    }
	}
}
