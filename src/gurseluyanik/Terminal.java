package gurseluyanik;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Terminal {
	// Define new CRS
    private static CRS crs = new CRS();
    // Define scanner for inputs
    private static Scanner scanner = new Scanner(System.in);
    // Define dateFormatter for Date instances
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    // Start method
    public static void start() {
        showMainMenu();
    }

    // Define showMainMenu method
    private static void showMainMenu() {
        while (true) {
        	// Print all menu options
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Doctor Operations");
            System.out.println("2. Rendezvous Operations");
            System.out.println("3. Patient Operations");
            System.out.println("4. Section Operations");
            System.out.println("5. Hospital Operations");
            System.out.println("6. Save");
            System.out.println("7. Load");
            System.out.println("8. Exit");
            System.out.print("Choose an operation (1-8): ");
            // Get choice from user
            int choice = getIntInput();
            switch (choice) {
                case 1: // Doctor Menu
                	showDoctorMenu(); 
                	break;
                case 2: // Rendezvous Menu
                	showRendezvousMenu(); 
                	break;
                case 3: // Patient Menu
                	showPatientMenu(); 
                	break;
                case 4: // Section Menu
                	showSectionMenu(); 
                	break;
                case 5: // Hospital Menu
                	showHospitalMenu(); 
                	break;
                case 6:  // Save Program
                	saveMenu();
                	break;
                case 7: // Load Program
                	loadMenu();
                	break;
                case 8: // Exit Program
                    System.out.println("Are you sure to exit? (y/n)");
                    if (scanner.next().toLowerCase().charAt(0) == 'y') {
                        System.exit(0);
                    }
                    break;
                default: System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    // Define saveMenu method
    private static void saveMenu() {
    	String filePath;
    	while(true) {
    		System.out.println("\n=== Save Menu ===");
    		System.out.println("Path of File: ");
    		filePath = scanner.next().trim(); // Get input and delete spaces from input
    		try {
				crs.saveTablesToDisk(filePath);
				System.out.println("Saving is done!");
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }

    // Define loadMenu method
    private static void loadMenu() {
    	String filePath;
    	while (true) {
    		System.out.println("\n=== Load Menu ===");
    		System.out.println("Path of File: ");
    		filePath = scanner.next().trim(); // Get input and delete spaces from input
    		try {
				crs.loadTablesFromDisk(filePath);
				System.out.println("Loading is done!");
				return;
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
    	}
    }
    
    // Define showDoctorMenu method
    private static void showDoctorMenu() {
        while (true) {
        	// Print all menu options
            System.out.println("\n=== Doctor Menu ===");
            System.out.println("1. Add Doctor");
            System.out.println("2. Show Doctors");
            System.out.println("3. Show Doctor Schedule");
            System.out.println("4. Return to Main Menu");
            System.out.print("Choose an operation (1-4): ");
            // Get choice from user
            int choice = getIntInput();
            switch (choice) {
                case 1: // Add Doctor
                	addDoctor(); 
                	break;
                case 2: // Show Doctors
                	showDoctors(); 
                	break;
                case 3: // Show Doctor Schedule
                	showDoctorSchedule(); 
                	break;
                case 4: 
                	return; // Go back main Menu
                default: System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Define addDoctor menu method
    private static void addDoctor() {
    	// Control is there any hospital
        if (crs.getHospitals().isEmpty()) {
            System.out.println("Error: There is no hospital in program.");
            return;
        }
        // Control is there any section
        boolean hasSection = false;
        for (Hospital hospital : crs.getHospitals().values()) {
            if (!hospital.getSections().isEmpty()) {
                hasSection = true;
            }
        }
        // if there is no section, we go back to main menu
        if (!hasSection) {
            System.out.println("Error: All hospitals don't have any section.");
            return;
        }
        // Add Doctor Menu Start
        System.out.println("\n=== Add Doctor ===");
        // Get Name
        System.out.print("Enter doctor name: ");
        String name = scanner.next();
        // Get National ID
        System.out.print("Enter national ID: ");
        long nationalId = getLongInput();
        // Get Diploma ID
        System.out.print("Enter diploma ID: ");
        int diplomaId = getIntInput();
        // Get maxPatient number
        System.out.print("Enter max patients per day: ");
        int maxPatients = getIntInput();
        // Show available hospitals and sections
        System.out.println("\nAvailable Hospitals and Sections:");
        for (Hospital hospital : crs.getHospitals().values()) {
            System.out.println("Hospital " + hospital.getID() + ": " + hospital.getName());
            for (Section section : hospital.getSections()) {
                System.out.println(" \tSection " + section.getID() + ": " + section.getName());
            }
        }
        // Get Hospital ID
        System.out.print("Enter hospital ID: ");
        int hospitalId = getIntInput();
        // Get Section ID
        System.out.print("Enter section ID: ");
        int sectionId = getIntInput();
        try {
            crs.addDoctorToSection(name, nationalId, diplomaId, maxPatients, hospitalId, sectionId);
            System.out.println("Doctor added successfully.");
        } catch (DuplicateInfoException e) {
            System.err.println("Error: " + e.getMessage()); // Print error message for DuplicateInfoException
        }
    }

    // Define showDoctors menu method
    private static void showDoctors() {
        System.out.println("\n=== All Doctors ===");
        System.out.printf("%-10s %-20s %-15s %-20s%n", "DiplomaID", "Name", "NationalID", "Section");
        System.out.println("------------------------------------------------------------");
        
        // Get doctor for information
        for (Hospital hospital : crs.getHospitals().values()) {
            for (Section section : hospital.getSections()) {
                for (Doctor doctor : section.getDoctors()) {
                	// Print all informations about doctor
                    System.out.printf("%-10d %-20s %-15d %-20s%n",
                        doctor.getDiplomaID(),
                        doctor.getName(),
                        doctor.getNationalID(),
                        doctor.getSection()
                    );
                }
            }
        }
    }
    
    // Define showDoctorSchedule menu method
    private static void showDoctorSchedule() {
        System.out.println("\n=== Doctor Schedule ===");
        // List all doctors
        System.out.println("Available Doctors:");
        int numberOfDoctor = 1;
        for (Hospital hospital : crs.getHospitals().values()) {
            for (Section section : hospital.getSections()) {
                for (Doctor doctor : section.getDoctors()) {
                    System.out.println(numberOfDoctor++ + ". " + doctor.getName() + " (Diploma ID: " + doctor.getDiplomaID() + ")");
                }
            }
        }
        // Get input from user for which doctor's schedule will show
        System.out.print("Select doctor (enter number): ");
        int choice = getIntInput();
        
        // Find selected doctor
        Doctor selectedDoctor = null; // Define a Doctor instance
        numberOfDoctor = 1;
        // to find desired doctor, we need to look all doctors until find that doctor
        findDoctor: for (Hospital hospital : crs.getHospitals().values()) {
            for (Section section : hospital.getSections()) {
                for (Doctor doctor : section.getDoctors()) {
                    if (numberOfDoctor == choice) {
                        selectedDoctor = doctor;
                        break findDoctor;
                    }
                    numberOfDoctor++; // Look doctor and if that doctor isn't our doctor, we try next doctor
                }
            }
        }
        // if we can find doctor
        if (selectedDoctor != null) {
            System.out.println("\nSchedule for Dr. " + selectedDoctor.getName());
            System.out.printf("%-15s %-20s%n", "Date", "Patient Name");
            System.out.println("--------------------------------");
            // Print Schedule
            for (Rendezvous session : selectedDoctor.getSchedule().getSessions()) {
                System.out.printf("%-15s %-20s%n",
                    dateFormat.format(session.getDateTime()),
                    session.getPatient().getName()
                );
            }
        } else { // if we can't find doctor
            System.out.println("Invalid selection."); // Error message
        }
    }
    
    // Define showRendezvousMenu method
    private static void showRendezvousMenu() {
        while (true) {
            System.out.println("\n=== Rendezvous Menu ===");
            System.out.println("1. Make Rendezvous");
            System.out.println("2. Show Rendezvous");
            System.out.println("3. Return to Main Menu");
            System.out.print("Choose an operation (1-3): ");
            // Get choice from user
            int choice = getIntInput();
            switch (choice) {
                case 1: // Make Rendezvous
                	makeRendezvous(); 
                	break;
                case 2: // Show Rendezvous
                	showRendezvous(); 
                	break;
                case 3: // Go back to Main Menu
                	return;
                default: System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Define makeRendezvous method
    private static void makeRendezvous() {
    	// Control is there any patient
        if (crs.getPatients().isEmpty()) {
            System.out.println("Error: No patients in the system.");
            return;
        }
        System.out.println("\n=== Make Rendezvous ===");
        System.out.print("Enter patient ID: ");
        int patientId = getIntInput();
        System.out.print("Enter hospital ID: ");
        int hospitalId = getIntInput();
        System.out.print("Enter section ID: ");
        int sectionId = getIntInput();
        System.out.print("Enter doctor diploma ID: ");
        int diplomaId = getIntInput();
        System.out.print("Enter date (dd/MM/yyyy): ");
        String dateInput = scanner.next();
        // try-catch for makeRendezvous method
        try {
            Date date = dateFormat.parse(dateInput);
            if (crs.makeRandezvous(patientId, hospitalId, sectionId, diplomaId, date)) {
                System.out.println("Rendezvous created successfully.");
            } else { // Maybe doctor schedule is full for that day
                System.out.println("Error: Failed to create rendezvous. Doctor may be unavailable.");
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please try again.");
        } catch (IDException e) {
        	System.err.println("Error: " + e.getMessage()); // Print error message for IDException
        }
    }
    
    // Define showRendezvous method
    private static void showRendezvous() {
        System.out.println("\n=== All Rendezvous ===");
        System.out.printf("%-15s %-20s %-20s%n", "Date", "Patient Name", "Doctor Name");
        System.out.println("------------------------------------------------------------");
        // Print all rendezvous
        for (Rendezvous rendezvous : crs.getRendezvous()) {
            System.out.printf("%-15s %-20s %-20s%n", dateFormat.format(rendezvous.getDateTime()), rendezvous.getPatient().getName(), rendezvous.getDoctor().getName());
        }
    }

    // Define showPatientMenu method
    private static void showPatientMenu() {
        while (true) {
            System.out.println("\n=== Patient Menu ===");
            System.out.println("1. Add Patient");
            System.out.println("2. Show Patients");
            System.out.println("3. Return to Main Menu");
            System.out.print("Choose an operation (1-3): ");
            // Get choice from user
            int choice = getIntInput();
            switch (choice) {
                case 1: // Add Patient
                	addPatient(); 
                	break;
                case 2: // Show Patient
                	showPatients(); 
                	break;
                case 3: // Go back to Main Menu
                	return;
                default: System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Define addPatient method
    private static void addPatient() {
        System.out.println("\n=== Add Patient ===");
        // Get name
        System.out.print("Enter patient name: ");
        String name = scanner.next();
        // Get nationalID
        System.out.print("Enter national ID: ");
        long nationalID = getLongInput();
        // Add Patient
        crs.addPatient(name, nationalID);
        System.out.println("Patient added successfully.");
    }

    // Define showPatients method
    private static void showPatients() {
        System.out.println("\n=== All Patients ===");
        System.out.printf("%-20s %-15s%n", "Name", "National ID");
        System.out.println("---------------------------------------");
        // Print all patients
        for (Patient patient : crs.getPatients().values()) {
            System.out.printf("%-20s %-15d%n", patient.getName(), patient.getNationalID());
        }
    }

    // Define showSectionMenu method
    private static void showSectionMenu() {
        while (true) {
            System.out.println("\n=== Section Menu ===");
            System.out.println("1. Add Section");
            System.out.println("2. Show Sections");
            System.out.println("3. Return to Main Menu");
            System.out.print("Choose an operation (1-3): ");
            // Get choice from user
            int choice = getIntInput();
            switch (choice) {
                case 1: // Add Section
                	addSection(); 
                	break;
                case 2: // Show Section
                	showSections(); 
                	break;
                case 3: // Go back to Main Menu
                	return;
                default: System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    // Define addSection method
    private static void addSection() {
        System.out.println("\n=== Add Section ===");
        // Get name
        System.out.print("Enter section name: ");
        String name = scanner.next();
        // Get hospitalID
        System.out.print("Enter hospital ID: ");
        int hospitalID = getIntInput();
        // Add Section to Hospital
        crs.addSectionToHospital(name, hospitalID);
        System.out.println("Section added successfully.");
    }

    // Define showSections method
    private static void showSections() {
        System.out.println("\n=== All Sections ===");
        System.out.printf("%-10s %-20s %-20s%n", "ID", "Name", "Hospital Name");
        System.out.println("------------------------------------------------------------");
        // Print all sections
        for (Hospital hospital : crs.getHospitals().values()) {
            for (Section section : hospital.getSections()) {
                System.out.printf("%-10d %-20s %-20s%n", section.getID(), section.getName(), hospital.getName());
            }
        }
    }

    // Define showHospitalMenu
    private static void showHospitalMenu() {
        while (true) {
            System.out.println("\n=== Hospital Menu ===");
            System.out.println("1. Add Hospital");
            System.out.println("2. Show Hospitals");
            System.out.println("3. Return to Main Menu");
            System.out.print("Choose an operation (1-3): ");
            // Get choice from user
            int choice = getIntInput();
            switch (choice) {
                case 1: // Add Hospital
                	addHospital(); 
                	break;
                case 2: // Show Hospital
                	showHospitals(); 
                	break;
                case 3: // Go back to Main Menu
                	return;
                default: System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Define addHospital method
    private static void addHospital() {
        System.out.println("\n=== Add Hospital ===");
        // Get name
        System.out.print("Enter hospital name: ");
        String name = scanner.next();
        // Add Hospital
        crs.addHospital(name);
        System.out.println("Hospital added successfully.");
    }

    // Define showHospitals method
    private static void showHospitals() {
        System.out.println("\n=== All Hospitals ===");
        System.out.printf("%-10s %-20s%n", "ID", "Name");
        System.out.println("-------------------------------------");
        // Print all hospitals
        for (Hospital hospital : crs.getHospitals().values()) {
            System.out.printf("%-10d %-20s%n", hospital.getID(), hospital.getName());
        }
    }
    
    // Methods for get input from user
    private static int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
    private static long getLongInput() {
        while (true) {
            try {
                return Long.parseLong(scanner.next());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}
