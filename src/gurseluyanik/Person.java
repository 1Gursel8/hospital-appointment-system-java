package gurseluyanik;

import java.io.Serializable;

public class Person implements Serializable {

	// Define attributes of Person class and serialVersionUID
	private static final long serialVersionUID = 1L;
	private String name;
	private final long national_id;
	
	// Define constructor of Person class
	public Person (String name, long national_id) {
		this.name = name;
		this.national_id = national_id;
	}
	
	// Define getter for name
	public String getName() {
		return this.name;
	}
	
	// Define getter for national_id
	public long getNationalID() {
		return this.national_id;
	}
	
	// Define (Override) toString method of Person class, output:
	// Name: name
	// National ID: national_id
	public String toString() {
		return "Name: " + this.name;
	}
}
