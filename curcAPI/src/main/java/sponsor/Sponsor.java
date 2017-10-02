package sponsor;
import java.util.ArrayList;

import study.Study;

public class Sponsor {
	
	private final int sponsorID;
	private String name;
	private ArrayList<Study> studies;
	
	public Sponsor(int sponsorID, String name) {
		
		this.sponsorID = sponsorID;
		this.name = name;
		studies = new ArrayList<>();
		
	}
	
	private int getID() {
		return sponsorID;
	}
	
	private String getName() {
		return name;
	}
	
	private ArrayList<Study> getStudies() {
		return studies;
	}
	
	private void addStudy(Study study) {
		//perform some kind of check to make sure study isn't already assigned to a sponsor
		studies.add(study);
	}
	
	private void removeStudy(Study study) {
		studies.remove(study);
	}

}
