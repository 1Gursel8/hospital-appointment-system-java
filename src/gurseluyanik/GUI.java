package gurseluyanik;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class GUI {
	
	// Define CRS for program
	private static CRS crs = new CRS();
	
	// Define a JFrame
	public static JFrame window;
	
	// Define pixel numbers of the window
	private final static int windowX = 1280, windowY = 720;
	
	// Define addButton method
	public static JButton addButton(String buttonName, int x, int y, int lX, int lY) {
		JButton button = new JButton(buttonName);
		window.add(button);
		button.setBounds(x, y, lX, lY);
		return button;
	}
	
	// Define addLabel method
	public static void addLabel(String message, int x, int y, int lX, int lY) {
		JLabel label = new JLabel(message);
		window.add(label);
		label.setBounds(x, y, lX, lY);
	}
	
	// To add label that shows student info, defined that method
	public static void addStudentText() {
		addLabel("Gürsel Uyanık 22011012", 1100, 640, 180, 60);
	}
	
	// Define addTextField method
	public static JTextField addTextField(int x, int y, int lX, int lY) {
		JTextField textField = new JTextField();
		window.add(textField);
		textField.setBounds(x, y, lX, lY);
		return textField;
	}
	
	// Define infoMessage method
	public static void infoMessage(String message) {
		JOptionPane.showMessageDialog(window, message);
	}
	
	// Define errorMessage method
	public static void errorMessage(String message) {
		JOptionPane.showMessageDialog(window, message, "Hata", JOptionPane.ERROR_MESSAGE);
	}
	
	// Define main menu method
	public static void showMainMenu() {
		window.setVisible(false);
		window.setTitle("Main Menu"); // Set title for window
		window.getContentPane().removeAll(); // Clean window
		
		// Add buttons and labels
		// Doctor
		JButton doctorButton = addButton("Doctor", 100, 50, 160, 50);
		addLabel("You can access operations about doctors.", 300, 50, 300, 50);
		// Rendezvous
		JButton rendezvousButton = addButton("Rendezvous", 100, 125, 160, 50);
		addLabel("You can access operations about rendezvous.", 300, 125, 300, 50);
		// Patient
		JButton patientButton = addButton("Patient", 100, 200, 160, 50);
		addLabel("You can access operations about patients.", 300, 200, 300, 50);
		// Section
		JButton sectionButton = addButton("Section", 100, 275, 160, 50);
		addLabel("You can access operations about sections.", 300, 275, 300, 50);
		// Hospital
		JButton hospitalButton = addButton("Hospital", 100, 350, 160, 50);
		addLabel("You can access operations about hospitals.", 300, 350, 300, 50);
		// Save
		JButton saveButton = addButton("Save", 100, 475, 160, 50);
		addLabel("You can save program.", 300, 475, 300, 50);
		// Load
		JButton loadButton = addButton("Load", 100, 550, 160, 50);
		addLabel("You can load program.", 300, 550, 300, 50);
		// Exit
		JButton exitButton = addButton("Exit", 1095, 510, 160, 50);
		addLabel("Exit the program.", 1120, 560, 300, 50);
		
		// Add pathFile JTextField
		JTextField pathFile = addTextField(475, 540, 400 ,20);
		addLabel("Write path of file.", 620, 500, 300, 50);
		
		addStudentText();
		window.setVisible(true);
		
		// Add ActionListener for buttons
		// Doctor
		doctorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showDoctorMenu();
			}
		});
		// Rendezvous
		rendezvousButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showRendezvousMenu();
			}
		});
		// Patient
		patientButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPatientMenu();
			}
		});
		// Section
		sectionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showSectionMenu();
			}
		});
		// Hospital
		hospitalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showHospitalMenu();
			}
		});
		// Save
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					crs.saveTablesToDisk(pathFile.getText());
					infoMessage("Saving is done.");
					showMainMenu();
				} catch (IOException e1) {
					errorMessage("Please enter a valid path of file.");
				}
			}
		});
		// Load
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					crs.loadTablesFromDisk(pathFile.getText());
					infoMessage("Loading is done.");
					showMainMenu();
				} catch (ClassNotFoundException | IOException e1) {
					errorMessage("Please enter a valid path of file.");
				}
			}
		});
		// Exit
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(
						null,
						"Are you sure to exit?",
						"Exit",
						JOptionPane.YES_NO_OPTION
						);
				if(response == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
	}
	
	// Define Doctor menu method
	public static void showDoctorMenu() {
		window.setVisible(false);
		window.setTitle("Doctor Menu");
		window.getContentPane().removeAll();
		
		// Add buttons and labels
		// Add Doctor
		JButton addDoctorButton = addButton("Add Doctor", 100, 50, 160, 50);
		addLabel("You can add a doctor to doctor list.", 300, 50, 300, 50);
		// Show Doctors
		JButton showDoctorsButton = addButton("Show Doctors", 100, 125, 160, 50);
		addLabel("You can look all information about doctors.", 300, 125, 300, 50);
		// Show Doctor Schedule
		JButton showDoctorScheduleButton = addButton("Show Doctor Schedule", 100, 200, 160, 50);
		addLabel("You can look schedules of doctors.", 300, 200, 300, 50);
		// Main Menu
		JButton mainMenuButton = addButton("Main Menu", 100, 350, 160, 50);
		addLabel("You can go back to main menu.", 300, 350, 300, 50);
		
		// Add ActionListener for buttons
		// Add Doctor
		addDoctorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAddDoctorMenu();
			}
		});
		// Show Doctors
		showDoctorsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showShowDoctorsMenu();
			}
		});
		// Show Doctor Schedule
		showDoctorScheduleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showDoctorScheduleMenu();
			}
		});
		// Main Menu
		mainMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showMainMenu();
			}
		});
		
		addStudentText();
		window.setVisible(true);
	}
	
	// Define Add Doctor menu method
	public static void showAddDoctorMenu() {
		// Control is there any hospital
		if(!crs.getHospitals().isEmpty()) {
			boolean isEmpty = true;
			// Control is there any section
			for(Hospital hospital: crs.getHospitals().values()) {
				if(!hospital.getSections().isEmpty())
					isEmpty = false;
			}
			if(!isEmpty) {
				window.setVisible(false);
				window.setTitle("Add Doctor Menu");
				window.getContentPane().removeAll();
				
				// Add labels
				addLabel("Enter Information About The Doctor to be Added", 490, 15, 600, 50);
				addLabel("Name :", 450, 80, 100, 50);
				addLabel("National ID :", 450, 110, 100, 50);
				addLabel("Diploma ID :", 450, 140, 100, 50);
				addLabel("Max Patient Per Day :", 450, 170, 200, 50);
				addLabel("Section : ", 450, 200, 100, 50);
				
				// Add JTextField for inputs
				JTextField nameText = addTextField(500, 95, 350, 20);
				JTextField nationalText = addTextField(535, 125, 315, 20);
				JTextField diplomaText = addTextField(535, 155, 315, 20);
				JTextField maxPatientText = addTextField(585, 185, 265, 20);
				
				// Add JComboBox for select section
				JComboBox<Section> selectSection = new JComboBox<Section>();
				for(Hospital hospital: crs.getHospitals().values()) {
					for(Section section: hospital.getSections()) {
						selectSection.addItem(section);
					}
				}
				selectSection.setBounds(510, 205, 200, 50);
				window.add(selectSection);
				
				// Add buttons
				// Save Doctor
				JButton saveDoctorButton = addButton("Save Doctor", 570, 300, 160, 50);
				addLabel("Save doctor with given information.", 540, 350, 300, 50);
				// Doctor Menu
				JButton doctorMenuButton = addButton("Doctor Menu", 570, 450, 160, 50);
				addLabel("Go back to doctor menu.", 575, 500, 160, 50);
				
				// Add ActionListener for buttons
				// Save Doctor
				saveDoctorButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						boolean error = false; // To check is there any error
						long nationalID = 0; // Define variables
						int diplomaID = 0;
						int maxPatient = 0;
						Section selectedSection = (Section)selectSection.getSelectedItem();
						// Convert name input to String
						String name = nameText.getText();
						if(name.isEmpty()) {
							infoMessage("Please enter a valid name.");
							error = true;
						}
						// Convert nationalID input to long
						if(!error) {
							try {
								nationalID = Long.parseLong(nationalText.getText());
							}catch(NumberFormatException e1){
								errorMessage("Please enter a valid national ID.");
								error = true;
							}
						}	
						// Convert diplomaID input to integer
						if(!error) {
							try {
								diplomaID = Integer.parseInt(diplomaText.getText());
							}catch(NumberFormatException e2) {
								errorMessage("Please enter a valid diploma ID.");
							}
						}
						// Convert maxPatient input to integer
						if(!error) {
							try {
								maxPatient = Integer.parseInt(maxPatientText.getText());
							}catch(NumberFormatException e3) {
								errorMessage("Please enter a valid max patient per day value.");
								error = true;
							}
						}
						// Check is there any doctor with same diploma id
						if(!error) {
							for(Doctor doctor: selectedSection.getDoctors()) {
								if(doctor.getDiplomaID() == diplomaID) {
									errorMessage("In that section, there is a doctor with same diploma ID.");
									error = true;
								}
							}
						}
						// Add that doctor to Doctor list
						if(!error) {
							crs.addDoctorToSection(name, nationalID, diplomaID, maxPatient, 
									selectedSection.getHospital().getID(), selectedSection.getID());
							infoMessage("Doctor is added succesfully.");
							showAddDoctorMenu();
						}
					}
				});
				// Doctor Menu
				doctorMenuButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						showDoctorMenu();
					}
				});
						
				addStudentText();
				window.setVisible(true);
			}else {
				errorMessage("All hospitals don't have any section.");
			}
		}else {
			errorMessage("There is no hospital in program.");
		}
	}
	
	// Define Show Doctors menu method
	public static void showShowDoctorsMenu() {
		window.setVisible(false);
		window.setTitle("Show Doctors Menu");
		window.getContentPane().removeAll();
		
		// Define label
		addLabel("Doctors", 615, 2, 100, 50);
		
		// Define column names
		String[] columnNames = {"DiplomaID", "Name", "NationalID", "Section"};
		
		// Define TableModel for JTable
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
		
		// Add every doctor to row
		for(Hospital hospital: crs.getHospitals().values()) {
			for (Section section: hospital.getSections()) {
				for(Doctor doctor: section.getDoctors()) {
					Object[] row = {
							doctor.getDiplomaID(),
							doctor.getName(),
							doctor.getNationalID(),
							doctor.getSection()
					};
					tableModel.addRow(row);
				}
			}
		}
		
		// Define JTable
		JTable doctorTable = new JTable(tableModel);
		
		// Adjust JTable
		doctorTable.getColumnModel().getColumn(0).setPreferredWidth(100); // Adjust Diploma ID width
		doctorTable.getColumnModel().getColumn(1).setPreferredWidth(210); // Adjust Name width
		doctorTable.getColumnModel().getColumn(2).setPreferredWidth(100); // Adjust National ID width
		doctorTable.getColumnModel().getColumn(3).setPreferredWidth(210); // Adjust Section width
		doctorTable.setDefaultEditor(Object.class, null); // Prevents changes in the table
		
		// Add Scroll Pane
		JScrollPane scrollPane = new JScrollPane(doctorTable);
		window.add(scrollPane);
		scrollPane.setBounds(330, 50, 620, 440);
		
		// Add button and label for Doctor menu
		JButton doctorButton = addButton("Doctor Menu", 570, 510, 160, 50);
		addLabel("Go back to doctor menu.", 573, 550, 200, 50);
		// Add ActionListener for Doctor Menu
		doctorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showDoctorMenu();
			}
		});
		
		addStudentText();
		window.setVisible(true);
	}
	
	// Define Show Doctor Schedule menu method
	public static void showDoctorScheduleMenu() {
		window.setVisible(false);
		window.setTitle("Show Doctor Schedule Menu");
		window.getContentPane().removeAll();
		
		// Show Doctors' name to choose which doctor's schedule will show
		// First, define a JComboBox
		JComboBox<Doctor> selectDoctor = new JComboBox<Doctor>();
		// Then, add doctors from collections
		for(Hospital hospital: crs.getHospitals().values()) {
			for (Section section: hospital.getSections()) {
				for(Doctor doctor: section.getDoctors()) {
					selectDoctor.addItem(doctor);
				}
			}
		}
		// Set bounds for selectDoctor
		selectDoctor.setBounds(550, 50, 200, 50);
		window.add(selectDoctor);
		
		// Add ActionListener for selectDoctor
		selectDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				Doctor selectedDoctor = (Doctor) selectDoctor.getSelectedItem();
				// JTable for Schedule
				// Define column names
				String[] columnNames = {"Date", "Patient Name"};
				
				// Define TableModel for JTable
				DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
				
				// Add every doctor to row
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); // Date formatter for date data
				for (Rendezvous session: selectedDoctor.getSchedule().getSessions()) {
					Object[] row = {
							formatter.format(session.getDateTime()),
							session.getPatient().getName()
					};
					tableModel.addRow(row);
				}
				
				// Define JTable
				JTable scheduleTable = new JTable(tableModel);
				
				// Adjust JTable
				scheduleTable.getColumnModel().getColumn(0).setPreferredWidth(210); // Adjust Date width
				scheduleTable.getColumnModel().getColumn(1).setPreferredWidth(210); // Adjust Patient Name width
				scheduleTable.setDefaultEditor(Object.class, null); // Prevents changes in the table
				
				// Add Scroll Pane
				JScrollPane scrollPane = new JScrollPane(scheduleTable);
				window.add(scrollPane);
				scrollPane.setBounds(450, 100, 420, 380);
			}
		});
		
		// Add button and label for Doctor menu
		JButton doctorButton = addButton("Doctor Menu", 570, 500, 160, 50);
		addLabel("Go back to doctor menu.", 573, 550, 200, 50);
		// Add ActionListener for Doctor Menu
		doctorButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			showDoctorMenu();
			}
		});
		
		addStudentText();
		window.setVisible(true);
	}
	
	// Define Rendezvous menu method
	public static void showRendezvousMenu() {
		window.setVisible(false);
		window.setTitle("Rendezvous Menu");
		window.getContentPane().removeAll();
		
		// Add buttons and labels
		// Show Rendezvous
		JButton showRendezvousButton = addButton("Show Rendezvous", 100, 50, 160, 50);
		addLabel("You can look all rendezvous.", 300, 50, 300, 50);
		// Make Rendezvous
		JButton makeRendezvousButton = addButton("Make Rendezvous", 100, 125, 160, 50);
		addLabel("You can make a rendezvous for a patient.", 300, 125, 300, 50);
		// Main Menu
		JButton mainMenuButton = addButton("Main Menu", 100, 200, 160, 50);
		addLabel("You can go back to main menu.", 300, 200, 300, 50);
				
		// Add ActionListener for buttons
		// Show Rendezvous
		showRendezvousButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showShowRendezvousMenu();
			}
		});
		// Make Rendezvous
		makeRendezvousButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showMakeRendezvousMenu();
			}
		});
		// Main Menu
		mainMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showMainMenu();
			}
		});
		
		addStudentText();
		window.setVisible(true);
	}
	
	// Define Show Rendezvous menu method
	public static void showShowRendezvousMenu() {
		window.setVisible(false);
		window.setTitle("Show Rendezvous Menu");
		window.getContentPane().removeAll();
		
		// Define label
		addLabel("Rendezvous", 615, 2, 100, 50);
		
		// Define column names
		String[] columnNames = {"Date", "Patient Name", "Doctor Name"};
		
		// Define TableModel for JTable
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
		
		// Add every rendezvous to row
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); // Date formatter for date data
		for (Rendezvous rendezvous: crs.getRendezvous()) {
			Object[] row = {
					formatter.format(rendezvous.getDateTime()),
					rendezvous.getPatient().getName(),
					rendezvous.getDoctor().getName()
			};
			tableModel.addRow(row);
		}
		
		// Define JTable
		JTable rendezvousTable = new JTable(tableModel);
		
		// Adjust JTable
		rendezvousTable.getColumnModel().getColumn(0).setPreferredWidth(210); // Adjust Date width
		rendezvousTable.getColumnModel().getColumn(1).setPreferredWidth(210); // Adjust Patient Name width
		rendezvousTable.getColumnModel().getColumn(2).setPreferredWidth(210); // Adjust Doctor Name width
		rendezvousTable.setDefaultEditor(Object.class, null); // Prevents changes in the table
		
		// Add Scroll Pane
		JScrollPane scrollPane = new JScrollPane(rendezvousTable);
		window.add(scrollPane);
		scrollPane.setBounds(340, 50, 630, 440);
		
		// Add button and label for Doctor menu
		JButton rendezvousButton = addButton("Rendezvous Menu", 570, 510, 160, 50);
		addLabel("Go back to rendezvous menu.", 560, 550, 200, 50);
		// Add ActionListener for Doctor Menu
		rendezvousButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showRendezvousMenu();
			}
		});
				
		addStudentText();
		window.setVisible(true);
	}
	
	// Define Make Rendezvous menu method
	public static void showMakeRendezvousMenu() {
		if(!crs.getPatients().isEmpty()) {
			boolean isEmpty = true;
			for(Hospital hospital: crs.getHospitals().values()) {
				for(Section section: hospital.getSections()) {
					if(!section.getDoctors().isEmpty()) {
						isEmpty = false;
					}
				}
			}
			if(!isEmpty) {
				window.setVisible(false);
				window.setTitle("Make Rendezvous Menu");
				window.getContentPane().removeAll();
				
				// Add labels
				addLabel("Enter Information About The Rendezvous to be Added", 490, 15, 600, 50);
				addLabel("Patient :", 450, 80, 100, 50);
				addLabel("Doctor :", 450, 110, 100, 50);
				addLabel("Date :", 450, 140, 100, 50); // BURADA KALDIN!!!!!!!!!!!!!
				
				// Add JComboBox for select patient and doctor
				// Patient
				JComboBox<Patient> selectPatient = new JComboBox<Patient>();
				for(Patient patient: crs.getPatients().values()) {
					selectPatient.addItem(patient);
				}
				selectPatient.setBounds(510, 80, 500, 50);
				window.add(selectPatient);
				// Doctor
				JComboBox<Doctor> selectDoctor = new JComboBox<Doctor>();
				for (Hospital hospital: crs.getHospitals().values()) {
					for(Section section: hospital.getSections()) {
						for(Doctor doctor: section.getDoctors()) {
							selectDoctor.addItem(doctor);
						}
					}
				}
				selectDoctor.setBounds(510, 110, 500, 50);
				window.add(selectDoctor);
				
				// Add JTextField for date
				JTextField dateText = addTextField(510, 155, 100, 20);
				// Add informative text for input
				dateText.setText("dd/MM/yyyy");
				// When user click JTextField, dd/MM/yyyy will disappear. if there is a input, dd/MM/yyyy won't show up
				dateText.addFocusListener(new FocusListener() {
					public void focusGained(FocusEvent e) {
						if(dateText.getText().equals("dd/MM/yyyy")) {
							dateText.setText("");
						}
					}
					public void focusLost(FocusEvent e) {
						if(dateText.getText().isEmpty()) {
							dateText.setText("dd/MM/yyyy");
						}
						
					}
				});
				
				// Add slash for text automatically
				dateText.addKeyListener(new KeyAdapter() {
					public void keyTyped(KeyEvent e) {
						String date = dateText.getText();
						if((date.length() == 2) || (date.length() == 5)) {
							dateText.setText(date + "/");
						}
					}
				});
				
				// Add button and label for Make Rendezvous
				JButton makeRendezvousButton = addButton("Make Rendezvous", 550, 400, 160, 50);
				addLabel("Make Rendezvous with given information.",520 ,450, 200, 50);
				// Add ActionListener for Make Rendezvous
				makeRendezvousButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Patient selectedPatient = (Patient)selectPatient.getSelectedItem();
						Doctor selectedDoctor = (Doctor)selectDoctor.getSelectedItem();
						Date desiredDate = null;
						SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
						formatter.setLenient(false); // Prevent impossible dates like 32/10/1985
						try {
							desiredDate = formatter.parse(dateText.getText());
						}catch(Exception e1) {
							infoMessage("Please enter a valid date.");
						}
						if(crs.makeRandezvous(crs.getPatientID(selectedPatient), selectedDoctor.getSection().getHospital().getID(), 
								selectedDoctor.getSection().getID(), selectedDoctor.getDiplomaID(), desiredDate)) {
							infoMessage("Rendezvous is added succesfully");
							showMakeRendezvousMenu();
						}else {
							errorMessage("The doctor's schedule is full on that day.");
						}
					}
				});
				
				// Add button and label for Rendezvous menu
				JButton rendezvousButton = addButton("Rendezvous Menu", 420, 500, 160, 50);
				addLabel("Go back to rendezvous menu.", 410, 550, 200, 50);
				// Add ActionListener for Rendezvous Menu
				rendezvousButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						showRendezvousMenu();
					}
				});
				
				// Add button and label for Patient menu
				JButton patientButton = addButton("Patient Menu", 680, 500, 160, 50);
				addLabel("Go back to patient menu.", 682, 550, 200, 50);
				// Add ActionListener for Patient menu
				patientButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						showPatientMenu();
					}
				});
				
				addStudentText();
				window.setVisible(true);
			}else {
				errorMessage("There is no doctor in program.");
			}
		}else {
			errorMessage("There is no patient in program.");
		}
	}
	
	// Define Patient menu method
	public static void showPatientMenu() {
		window.setVisible(false);
		window.setTitle("Patient Menu");
		window.getContentPane().removeAll();
		
		// Add buttons and labels
		// Add Patient
		JButton addPatientButton = addButton("Add Patient", 100, 50, 160, 50);
		addLabel("You can add a patient to patient list.", 300, 50, 300, 50);
		// Show Patients
		JButton showPatientsButton = addButton("Show Patients", 100, 125, 160, 50);
		addLabel("You can look all information about patients.", 300, 125, 300, 50);
		// Make Rendezvous
		JButton makeRendezvousButton = addButton("Make Rendezvous", 100, 200, 160, 50);
		addLabel("You can make a rendezvous for a patient.", 300, 200, 300, 50);
		// Main Menu
		JButton mainMenuButton = addButton("Main Menu", 100, 350, 160, 50);
		addLabel("You can go back to main menu.", 300, 350, 300, 50);
		
		// Add ActionListener for buttons
		// Add Patient
		addPatientButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAddPatientMenu();
			}
		});
		//Show Patients
		showPatientsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showShowPatientsMenu();
			}
		});
		// Make Rendezvous
		makeRendezvousButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showMakeRendezvousMenu();
			}
		});
		// Main Menu
		mainMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showMainMenu();
			}
		});
		
		addStudentText();
		window.setVisible(true);
	}
	
	// Define Add Patient menu method
	public static void showAddPatientMenu() {
		window.setVisible(false);
		window.setTitle("Add Patient Menu");
		window.getContentPane().removeAll();
		
		// Add labels
		addLabel("Enter Information About The Patient to be Added", 490, 15, 600, 50);
		addLabel("Name :", 450, 80, 100, 50);
		addLabel("National ID :", 450, 110, 100, 50);
		
		// Add JTextField for inputs
		JTextField nameText = addTextField(500, 95, 350, 20);
		JTextField nationalText = addTextField(535, 125, 315, 20);
		
		// Add buttons
		// Save Patient
		JButton savePatientButton = addButton("Save Patient", 570, 250, 160, 50);
		addLabel("Save doctor with given information.", 540, 300, 300, 50);
		// Patient Menu
		JButton patientMenuButton = addButton("Patient Menu", 570, 400, 160, 50);
		addLabel("Go back to doctor menu.", 575, 450, 160, 50);
		
		// Add ActionListener for buttons
		// Save Patient
		savePatientButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean error = false; // To check is there any error
				long nationalID = 0; // Define variables
				// Convert name input to String
				String name = nameText.getText();
				if(name.isEmpty()) {
					infoMessage("Please enter a valid name.");
					error = true;
				}
				// Convert nationalID input to long
				if(!error) {
					try {
						nationalID = Long.parseLong(nationalText.getText());
					}catch(NumberFormatException e1){
						errorMessage("Please enter a valid national ID.");
						error = true;
					}
				}	
				// Add that patient to Patient HashMap
				if(!error) {
					crs.addPatient(name, nationalID);
					infoMessage("Patient is added succesfully.");
					showAddPatientMenu();
				}
			}
		});
		// Patient Menu
		patientMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPatientMenu();
			}
		});
		
		addStudentText();
		window.setVisible(true);
	}
	
	// Define Show Patients menu method
	public static void showShowPatientsMenu() {
		window.setVisible(false);
		window.setTitle("Show Patients Menu");
		window.getContentPane().removeAll();
		
		// Define label
		addLabel("Patients", 615, 2, 100, 50);
			
		// Define column names
		String[] columnNames = {"Name", "National ID", "Rendezvous Date", "Rendezvous Doctor"};
		
		// Define TableModel for JTable
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
		
		// Add every patient to row
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); // Date formatter for date data
		for (Patient patient: crs.getPatients().values()) {
			// if patient doesn't have rendezvous, then rendezvous will be blank
			if(patient.getRendezvous() == null) {
				Object[] row = {
						patient.getName(),
						patient.getNationalID()
				};
				tableModel.addRow(row);
			}else {
				Object[] row = {
						patient.getName(),
						patient.getNationalID(),
						formatter.format(patient.getRendezvous().getDateTime()),
						patient.getRendezvous().getDoctor().getName()
				};
				tableModel.addRow(row);
			}
		}
				
		// Define JTable
		JTable patientTable = new JTable(tableModel);
				
		// Adjust JTable
		patientTable.getColumnModel().getColumn(0).setPreferredWidth(210); // Adjust Name width
		patientTable.getColumnModel().getColumn(1).setPreferredWidth(100); // Adjust National width
		patientTable.getColumnModel().getColumn(2).setPreferredWidth(150); // Adjust Date width
		patientTable.getColumnModel().getColumn(3).setPreferredWidth(210);
		patientTable.setDefaultEditor(Object.class, null); // Prevents changes in the table
				
		// Add Scroll Pane
		JScrollPane scrollPane = new JScrollPane(patientTable);
		window.add(scrollPane);
		scrollPane.setBounds(310, 50, 670, 440);
		
		// Add button and label for Patient menu
		JButton patientButton = addButton("Patient Menu", 560, 510, 160, 50);
		addLabel("Go back to patient menu.", 563, 550, 200, 50);
		// Add ActionListener for Patient Menu
		patientButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPatientMenu();
			}
		});
		
		addStudentText();
		window.setVisible(true);
	}
	
	// Define Section menu method
	public static void showSectionMenu() {
		window.setVisible(false);
		window.setTitle("Section Menu");
		window.getContentPane().removeAll();
		
		// Add buttons and labels
		// Add Section
		JButton addSectionButton = addButton("Add Section", 100, 50, 160, 50);
		addLabel("You can add a section to section list.", 300, 50, 300, 50);
		// Show Sections
		JButton showSectionsButton = addButton("Show Sections", 100, 125, 160, 50);
		addLabel("You can look all sections.", 300, 125, 300, 50);
		// List Doctors
		JButton listDoctorsButton = addButton("List Doctors", 100, 200, 160, 50);
		addLabel("You can look all doctor from any section.", 300, 200, 300, 50);
		// Main Menu
		JButton mainMenuButton = addButton("Main Menu", 100, 350, 160, 50);
		addLabel("You can go back to main menu.", 300, 350, 300, 50);
		
		// Add ActionListener for buttons
		// Add Section
		addSectionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAddSectionMenu();
			}
		});
		// Show Sections
		showSectionsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showShowSectionsMenu();
			}
		});
		// List Doctors
		listDoctorsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showListDoctorsMenu();
			}
		});
		// Main Menu
		mainMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showMainMenu();
			}
		});

		addStudentText();
		window.setVisible(true);
	}
	
	// Define Add Section menu method
	public static void showAddSectionMenu() {
		// Control is there any hospital
		if(!crs.getHospitals().isEmpty()) {
			window.setVisible(false);
			window.setTitle("Add Section");
			window.getContentPane().removeAll();
			
			// Add labels
			addLabel("Enter Information About The Section to be Added", 490, 15, 600, 50);
			addLabel("Name :", 450, 80, 100, 50);
			addLabel("Hospital :", 450, 110, 100, 50);
			
			// Add JTextField for inputs
			JTextField nameText = addTextField(500, 95, 350, 20);
			
			// First, define a JComboBox
			JComboBox<Hospital> selectHospital = new JComboBox<Hospital>();
			// Then, add hospitals from HashMap
			for(Hospital hospital: crs.getHospitals().values()) {
				selectHospital.addItem(hospital);
			}
			// Set bounds for selectDoctor
			selectHospital.setBounds(520, 110, 200, 50);
			window.add(selectHospital);
			
			// Add buttons
			// Save Section
			JButton saveSectionButton = addButton("Save Section", 570, 250, 160, 50);
			addLabel("Save section with given information.", 540, 300, 300, 50);
			// Section Menu
			JButton sectionMenuButton = addButton("Section Menu", 570, 400, 160, 50);
			addLabel("Go back to section menu.", 575, 450, 160, 50);
			
			// Add ActionListener for buttons
			// Save Section
			saveSectionButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					boolean error = false; // To check is there any error
					Hospital selectedHospital = (Hospital)selectHospital.getSelectedItem();
					// Convert name input to String
					String name = nameText.getText();
					if(name.isEmpty()) {
						infoMessage("Please enter a valid name.");
						error = true;
					}
					// Check is there any section with same name
					if(!error) {
						for(Section section: selectedHospital.getSections()) {
							if(section.getName().equals(name)) {
								errorMessage("There is a section with same name in that hospital.");
								error = true;
							}
						}
					}
					// Add that section to Section list
					if(!error) {
						crs.addSectionToHospital(name, selectedHospital.getID());
						infoMessage("Section is added succesfully");
						showAddSectionMenu();
					}
				}
			});
			// Section Menu
			sectionMenuButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showSectionMenu();
				}
			});
			
			addStudentText();
			window.setVisible(true);
		}else {
			errorMessage("There is no hospital in program.");
		}

	}
	
	// Define Show Sections menu method
	public static void showShowSectionsMenu() {
		window.setVisible(false);
		window.setTitle("Show Sections");
		window.getContentPane().removeAll();
		
		// Define label
		addLabel("Sections", 610, 2, 100, 50);
		
		// Define column names
		String[] columnNames = {"Section ID", "Section Name", "Hospital Name"};
		
		// Define TableModel for JTable
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
		
		// Add every section to row
		for (Hospital hospital: crs.getHospitals().values()) {
			for(Section section: hospital.getSections()) {
				Object[] row = {
						section.getID(),
						section.getName(),
						hospital.getName()
				};
				tableModel.addRow(row);
			}
		}
		
		// Define JTable
		JTable sectionTable = new JTable(tableModel);
		
		// Adjust JTable
		sectionTable.getColumnModel().getColumn(0).setPreferredWidth(100); // Adjust Section ID width
		sectionTable.getColumnModel().getColumn(1).setPreferredWidth(210); // Adjust Section Name width
		sectionTable.getColumnModel().getColumn(2).setPreferredWidth(210); // Adjust Hospital Name width
		sectionTable.setDefaultEditor(Object.class, null); // Prevents changes in the table
		
		// Add Scroll Pane
		JScrollPane scrollPane = new JScrollPane(sectionTable);
		window.add(scrollPane);
		scrollPane.setBounds(380, 50, 520, 440);
		
		// Add button and label for Section menu
		JButton sectionButton = addButton("Section Menu", 560, 510, 160, 50);
		addLabel("Go back to section menu.", 563, 550, 200, 50);
		// Add ActionListener for Section Menu
		sectionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showSectionMenu();
			}
		});
		
		addStudentText();
		window.setVisible(true);
	}
	
	// Define List Doctors menu method
	public static void showListDoctorsMenu() {
		window.setVisible(false);
		window.setTitle("List Doctors");
		window.getContentPane().removeAll();
		
		// Show Sections to choose which section's doctors will show
		// First, define a JComboBox
		JComboBox<Section> selectSection = new JComboBox<Section>();
		// Then, add sections from LinkedList
		for(Hospital hospital: crs.getHospitals().values()) {
			for(Section section: hospital.getSections()) {
				selectSection.addItem(section);
			}
		}
		// Set bounds for selectSection
		selectSection.setBounds(550, 50, 200, 50);
		window.add(selectSection);
		
		// Add ActionListener for selectSection
		selectSection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				Section selectedSection = (Section)selectSection.getSelectedItem();
				// JTable for doctors
				// Define column names
				String[] columnNames = {"Diploma ID", "Doctor Name", "National ID"};
				
				// Define TableModel for JTable
				DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
				
				// Add every doctor to row
				for (Doctor doctor: selectedSection.getDoctors()) {
					Object[] row = {
							doctor.getDiplomaID(),
							doctor.getName(),
							doctor.getNationalID()
					};
					tableModel.addRow(row);
				}
				
				// Define JTable
				JTable doctorTable = new JTable(tableModel);
				
				// Adjust JTable
				doctorTable.getColumnModel().getColumn(0).setPreferredWidth(100); // Adjust Diploma ID width
				doctorTable.getColumnModel().getColumn(1).setPreferredWidth(210); // Adjust Doctor Name width
				doctorTable.getColumnModel().getColumn(2).setPreferredWidth(100); // Adjust National ID width
				doctorTable.setDefaultEditor(Object.class, null); // Prevents changes in the table
				
				// Add Scroll Pane
				JScrollPane scrollPane = new JScrollPane(doctorTable);
				window.add(scrollPane);
				scrollPane.setBounds(490, 100, 320, 380);
			}
		});
		
		// Add button and label for Section menu
		JButton sectionButton = addButton("Section Menu", 570, 500, 160, 50);
		addLabel("Go back to section menu.", 573, 550, 200, 50);
		// Add ActionListener for Section Menu
		sectionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showSectionMenu();
			}
		});
		
		addStudentText();
		window.setVisible(true);
	}
	
	// Define Hospital menu method
	public static void showHospitalMenu() {
		window.setVisible(false);
		window.setTitle("Hospital Menu");
		window.getContentPane().removeAll();
		
		// Add buttons and labels
		// Add Hospital
		JButton addHospitalButton = addButton("Add Hospital", 100, 50, 160, 50);
		addLabel("You can add hospital.", 300, 50, 300, 50);
		// Show Hospitals
		JButton showHospitalsButton = addButton("Show Hospitals", 100, 125, 160, 50);
		addLabel("You can look all hospitals.", 300, 125, 300, 50);
		// Main Menu
		JButton mainMenuButton = addButton("Main Menu", 100, 200, 160, 50);
		addLabel("You can go back to main menu.", 300, 200, 300, 50);
		
		// Add ActionListener for buttons
		// Add Hospital
		addHospitalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAddHospitalMenu();
			}
		});
		// Show Hospital
		showHospitalsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showShowHospitalsMenu();
;			}
		});
		// Main Menu
		mainMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showMainMenu();
			}
		});
		
		addStudentText();
		window.setVisible(true);
	}
	
	// Define Add Hospital menu method
	public static void showAddHospitalMenu() {
		window.setVisible(false);
		window.setTitle("Add Hospital Menu");
		window.getContentPane().removeAll();
		
		// Add labels
		addLabel("Enter Information About The Hospital to be Added", 490, 15, 600, 50);
		addLabel("Name :", 450, 80, 100, 50);
		
		// Add JTextField for inputs
		JTextField nameText = addTextField(500, 95, 350, 20);
		
		// Add buttons
		// Save Doctor
		JButton saveHospitalButton = addButton("Save Hospital", 570, 250, 160, 50);
		addLabel("Save hospital with given information.", 540, 300, 300, 50);
		// Doctor Menu
		JButton hospitalMenuButton = addButton("Hospital Menu", 570, 400, 160, 50);
		addLabel("Go back to hospital menu.", 575, 450, 160, 50);
		
		// Add ActionListener for buttons
		// Save Hospital
		saveHospitalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean error = false; // To check is there any error
				// Convert name input to String
				String name = nameText.getText();
				if(name.isEmpty()) {
					infoMessage("Please enter a valid name.");
					error = true;
				}
				// Add that hospital to Hospital list
				if(!error) {
					crs.addHospital(name);
					infoMessage("Hospital is added succesfully.");
					showAddHospitalMenu();
				}
			}
		});
		// Hospital Menu
		hospitalMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showHospitalMenu();
			}
		});
		
		addStudentText();
		window.setVisible(true);
	}
	
	// Define Show Hospitals menu method
	public static void showShowHospitalsMenu() {
		window.setVisible(false);
		window.setTitle("Show Hospitals Menu");
		window.getContentPane().removeAll();
		
		// Define label
		addLabel("Hospitals", 615, 2, 100, 50);
			
		// Define column names
		String[] columnNames = {"Hospital ID", "Name"};
		
		// Define TableModel for JTable
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
		
		// Add every hospital to row
		for (Hospital hospital: crs.getHospitals().values()) {
			Object[] row = {
					hospital.getID(),
					hospital.getName()
			};
			tableModel.addRow(row);
		}
				
		// Define JTable
		JTable hospitalTable = new JTable(tableModel);
				
		// Adjust JTable
		hospitalTable.getColumnModel().getColumn(0).setPreferredWidth(100); // Adjust ID width
		hospitalTable.getColumnModel().getColumn(1).setPreferredWidth(210); // Adjust Name width
		hospitalTable.setDefaultEditor(Object.class, null); // Prevents changes in the table
				
		// Add Scroll Pane
		JScrollPane scrollPane = new JScrollPane(hospitalTable);
		window.add(scrollPane);
		scrollPane.setBounds(390, 50, 520, 440);
		
		// Add button and label for Patient menu
		JButton hospitalButton = addButton("Hospital Menu", 570, 510, 160, 50);
		addLabel("Go back to hospital menu.", 573, 550, 200, 50);
		// Add ActionListener for Patient Menu
		hospitalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showHospitalMenu();
			}
		});
		
		addStudentText();
		window.setVisible(true);
	}
	
	// Define startGUI method
	public static void startGUI() {
		// Start JFrame named "NYP Project 22011012"
		window = new JFrame("NYP Project 22011012");
		// Set window's size, 1280x720
		window.setSize(windowX, windowY);			
		// Set default exit operation
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
		// Set a layout for JFrame, null = FlowLayout
		window.setLayout(null);		
		// Clean window
		window.getContentPane().removeAll();		
		// Make window visible
		window.setVisible(true);		
		showMainMenu();
	}

}

