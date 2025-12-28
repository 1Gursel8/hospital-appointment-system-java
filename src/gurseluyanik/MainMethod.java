/*
 * ======================================================================================
 * File: MainMethod.java
 * Project: Hospital Appointment System (CRS)
 * Author: Gürsel Uyanık
 * GitHub: https://github.com/1Gursel8
 *
 * Description:
 * This project is a comprehensive Hospital Appointment System developed in Java.
 * It features a dual-interface architecture offering both a Graphical User Interface (Swing)
 * and a Command Line Interface (Terminal). Key engineering concepts include
 * Object-Oriented Programming (Inheritance, Polymorphism), Exception Handling,
 * File I/O (Serialization), and Unit Testing with JUnit.
 * ======================================================================================
 */

package gurseluyanik;

import java.util.Scanner;

public class MainMethod {
	
	// Define scanner for input
	private static Scanner scanner = new Scanner(System.in);
	// Method for input
	private static int getIntInput() {
		while (true) {
			try {
				return Integer.parseInt(scanner.next());
	        } catch (NumberFormatException e) {
	        	System.out.print("Please enter a valid number: ");
	        }
		}
	}

	public static void main(String[] args) {
		while (true) {
			// Print all menu options
	        System.out.println("\n=== GUI or Terminal ===");
	        System.out.println("1. GUI Mode");
	        System.out.println("2. Terminal Mode");
	        System.out.println("3. Exit");
	        System.out.print("Choose a Mode (1 or 2): ");
	        // Get choice from user
	        int choice = getIntInput();
	        switch (choice) {
	          	case 1: // GUI Mode
	                GUI.startGUI();
	          		break;
	          	case 2: // Terminal Mode
	          		Terminal.start();
	                break;
	            case 3: // Exit
	            	System.out.println("Are you sure to exit? (y/n)");
	                if (scanner.next().toLowerCase().charAt(0) == 'y') {
	                	System.exit(0);
	                }
	                break;
	            default: System.out.println("Invalid choice. Please try again.");
	        }
		}
	}
}
