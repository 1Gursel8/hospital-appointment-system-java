package gurseluyanik;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class Tests {
	
	// Tests for CRS class
	@Test
	void testCRSMakeRendezvous() {
		//Create a new instance for CRS
		CRS testCRS = new CRS();
		// Add a hospital, section, doctor and patient
		testCRS.addHospital("Test Hospital");
		testCRS.addSectionToHospital("Test Section", 1);
		testCRS.addDoctorToSection("TestDoctor", 1, 1, 1, 1, 1);
		testCRS.addPatient("TestPatient", 1);
		// Try to make rendezvous with wrong id
		Exception exception = assertThrows(IDException.class, () -> {
			testCRS.makeRandezvous(1, 1, 1, 0, new Date()); // Diploma ID is wrong
		});
		// Control message
		assertEquals("Not Found Doctor Diploma ID", exception.getMessage());
	}
	
	@Test
	void testCRSLoad() {
		// Create a new instance for CRS
		CRS testCRS = new CRS();
		// Try to load with wrong path of file
		Exception exception = assertThrows(IOException.class, () -> {
			testCRS.loadTablesFromDisk("wrong/path/of/file.ser");
		});
		// Control message
		assertEquals("File can't be found: wrong/path/of/file.ser", exception.getMessage());
	}
	
	// Tests for Hospital class
	@Test
	void testHospitalAddSection() {
		// Create a new instance for Hospital
		Hospital testHospital = new Hospital(1, "Test Hospital");
		// Define a section with "Test" name
		testHospital.addSection(new Section (1, "Test", testHospital));
		// Try to add "Test" section with different id
		Exception exception = assertThrows(DuplicateInfoException.class, () -> {
			testHospital.addSection(new Section (2, "Test", testHospital));
		});
		// Control message
		assertEquals("The section you try to add already has added to sections.", exception.getMessage());
	}
	
	@Test
	void testHospitalGetSection() {
		// Create a new instance for Hospital
		Hospital testHospital = new Hospital(1, "Test Hospital");
		// Define a section
		Section testSection = new Section(1, "Test", testHospital);
		// Add section to hospital
		testHospital.addSection(testSection);
		// Control sectionID
		assertEquals(testSection, testHospital.getSection(1));
	}
	
	// Tests for Section class
	@Test
	void testSectionAddDoctor() {
		// Create a new instances for Hospital and Section
		Hospital testHospital = new Hospital(1, "Test Hospital");
		Section testSection = new Section(1, "Test Section", testHospital);
		// Add a doctor with diplomaID 1
		testSection.addDoctor(new Doctor("Test1", 1, 1, 1, testSection));
		// Try to add another doctor with same diploma ID
		Exception exception = assertThrows(DuplicateInfoException.class, () -> {
			testSection.addDoctor(new Doctor("Test2", 1, 1, 1, testSection));
		});
		// Control message
		assertEquals("There is a doctor has same diploma ID with doctor that you try to add.", exception.getMessage());
	}
	
	@Test
	void testSectionGetDoctors() {
		// Create a new instances for Hospital and Section
		Hospital testHospital = new Hospital(1, "Test Hospital");
		Section testSection = new Section(1, "Test Section", testHospital);
		// Create and add two doctors
		Doctor testDoctor1 = new Doctor("Test1", 1, 1, 1, testSection);
		Doctor testDoctor2 = new Doctor("Test2", 1, 2, 2, testSection);
		testSection.addDoctor(testDoctor1);
		testSection.addDoctor(testDoctor2);
		// Control Doctor List
		assertEquals(testDoctor1, testSection.getDoctors().get(0));
	}
	
	// Tests for Doctor class
	@Test
	void testDoctorGetSection() {
		// Create a new instances for Hospital, Section and Doctor
		Hospital testHospital = new Hospital(1, "Test Hospital");
		Section testSection = new Section(1, "Test Section", testHospital);
		Doctor testDoctor = new Doctor("Test", 1, 1, 1, testSection);
		// Control Section
		assertEquals(testSection, testDoctor.getSection());
	}
	
	@Test
	void testDoctorDiplomaID() {
		// Create a new instances for Hospital, Section and Doctor
		Hospital testHospital = new Hospital(1, "Test Hospital");
		Section testSection = new Section(1, "Test Section", testHospital);
		Doctor testDoctor = new Doctor("Test", 1, 24, 1, testSection);
		// Control diplomaID
		assertEquals(24, testDoctor.getDiplomaID());
	}
	
	// Tests for Schedule class
	@Test
	void testScheduleGetDoctor() {
		// Create a new instances for Hospital, Section and Doctor
		Hospital testHospital = new Hospital(1, "Test Hospital");
		Section testSection = new Section(1, "Test Section", testHospital);
		Doctor testDoctor = new Doctor("Test", 1, 1, 1, testSection);
		// Create a new instance for Schedule and set doctor
		Schedule testSchedule = new Schedule(1);
		testSchedule.setDoctor(testDoctor);
		// Control Doctor
		assertEquals(testDoctor, testSchedule.getDoctor());
	}
	
	@Test
	void testScheduleAddRendezvous() {
		// Create a new instances for Hospital, Section and Doctor
		Hospital testHospital = new Hospital(1, "Test Hospital");
		Section testSection = new Section(1, "Test Section", testHospital);
		Doctor testDoctor = new Doctor("Test", 1, 1, 1, testSection);
		// Create a new instance for Schedule and set doctor
		Schedule testSchedule = new Schedule(1);
		testSchedule.setDoctor(testDoctor);
		// Create two instances for Patient
		Patient testPatient1 = new Patient("Test1", 1);
		Patient testPatient2 = new Patient("Test2", 2);
		// Create a new instance for Date
		Date testDate = new Date(2024-1900,0,13);
		testSchedule.addRendezvous(testPatient1, testDate);
		// Control adding failure
		assertEquals(false, testSchedule.addRendezvous(testPatient2, testDate));
	}
	
	// Tests for Rendezvous class
	@Test
	void testRendezvousGetPatient() {
		// Create a new instances for Hospital, Section and Doctor
		Hospital testHospital = new Hospital(1, "Test Hospital");
		Section testSection = new Section(1, "Test Section", testHospital);
		Doctor testDoctor = new Doctor("Test", 1, 1, 1, testSection);
		// Create a new instance for Patient
		Patient testPatient = new Patient("Test", 1);
		// Create a new instance for Date
		Date testDate = new Date(2024-1900,0,13);
		// Create a new instance for Rendezvous
		Rendezvous testRendezvous = new Rendezvous(testDate, testDoctor, testPatient);
		// Control Patient
		assertEquals(testPatient, testRendezvous.getPatient());
	}
	
	@Test
	void testRendezvousGetDate() {
		// Create a new instances for Hospital, Section and Doctor
		Hospital testHospital = new Hospital(1, "Test Hospital");
		Section testSection = new Section(1, "Test Section", testHospital);
		Doctor testDoctor = new Doctor("Test", 1, 1, 1, testSection);
		// Create a new instance for Patient
		Patient testPatient = new Patient("Test", 1);
		// Create a new instance for Date
		Date testDate = new Date(2024-1900,0,13);
		// Create a new instance for Rendezvous
		Rendezvous testRendezvous = new Rendezvous(testDate, testDoctor, testPatient);
		// Control Date
		assertEquals(testDate, testRendezvous.getDateTime());
	}

	// Tests for Patient class
	@Test
	void testPatientGetRendezvous() {
		// Create a new instances for Hospital, Section and Doctor
		Hospital testHospital = new Hospital(1, "Test Hospital");
		Section testSection = new Section(1, "Test Section", testHospital);
		Doctor testDoctor = new Doctor("Test", 1, 1, 1, testSection);
		// Create a new instance for Patient
		Patient testPatient = new Patient("Test", 1);
		// Create a new instance for Date
		Date testDate = new Date(2024-1900,0,13);
		// Create a new instance for Rendezvous
		Rendezvous testRendezvous = new Rendezvous(testDate, testDoctor, testPatient);
		// Set rendezvous for patient
		testPatient.setRendezvous(testRendezvous);
		// Control Rendezvous
		assertEquals(testRendezvous, testPatient.getRendezvous());
	}
	
	@Test
	void testPatientGetName() {
		// Create a new instance for Patient
		Patient testPatient = new Patient("Test", 1);
		// Control name
		assertEquals("Test", testPatient.getName());
	}
	
	// Tests for Person class
	@Test
	void testPersonGetName() {
		// Create a new instance for Person
		Person testPerson = new Person("Test", 1);
		// Control name
		assertEquals("Test", testPerson.getName());
	}
	
	@Test
	void testPersonGetID() {
		// Create a new instance for Person
		Person testPerson = new Person("Test", 1);
		// Control nationalID
		assertEquals(1 , testPerson.getNationalID());
	}
}
