package gurseluyanik;

import java.io.Serializable;
import java.util.LinkedList;

public class Hospital implements Serializable{

	// Define attributes of Hospital class and serialVersionUID
	private static final long serialVersionUID = 1L;
	private final int id;
	private String name;
	private LinkedList<Section> sections;
	
	
	public Hospital (int hospitalID, String name) {
		this.id = hospitalID;
		this.name = name;
		sections = new LinkedList<Section>();
	}
	
	// Define getter for name
	public String getName() {
		return this.name;
	}
	
	// Define getter for id
	public int getID() {
		return this.id;
	}
	
	// Define getter for sections
	public LinkedList<Section> getSections(){
		return this.sections;
	}
	
	// We define getSection(integer) method to find Section with given id
	public Section getSection(int id) {
		for(Section section: sections) {
			if(section.getID() == id) {
				return section;
			}
		}
		return null; // if there is no Section with that id, we will return null
	}
	
	// We define (Overload) getSection(String) method to find Section with given name. This method will be private, so we need to use it in class
	private Section getSection(String name) {
		for(Section section: sections) {
			if(section.getName() == name) {
				return section;
			}
		}
		return null; // if there is no Section with that id, we will return null
	}
	
	// Define addSection method, we will use getSection(String) method in this method. There won't be two doctors with same name
	public void addSection(Section section) {
		
		// if getSection method's output is null, then our Section isn't in sections
		if(getSection(section.getName()) == null) {
			this.sections.add(section);
			this.sections.sort((a, b) -> { return 1 * a.getName().compareTo(b.getName()); } ); // Sort sections by name
		}
		else {
			throw new DuplicateInfoException("The section you try to add already has added to sections.");
		}
	}
	
	// Define (Override) toString method for Hospital class
	public String toString() {
		return this.name;
	}
}
