package sponsor;
import java.util.ArrayList;

import study.Study;

public class Sponsor {
	
	private int sponsorID;
	private String name;
	
	public Sponsor(String name) {
		this.name = name;
	}
	
	
	public Sponsor(int sponsorID, String name) {
		this.sponsorID = sponsorID;
		this.name = name;
	}
	
	private int getID() { return sponsorID; }

	String getName() { return name; }
	private void setName(String name) { this.name = name; }

}
