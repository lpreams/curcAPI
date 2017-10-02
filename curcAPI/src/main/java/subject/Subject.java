package subject;
import java.util.ArrayList;
import java.util.Date;

import encounter.Encounter;
import study.Study;

//inherit Patient and Study / Protocol

public class Subject {

	private ArrayList<Encounter> encounters;
	private Study study;
	private String subjectNumber;
	private Date startDate;
	private Date stopDate;
	
	private Subject(Study study, String subjectNumber, Date startDate) {
		//just pass through a Patient and take what you need?
		this.study = study;
		this.subjectNumber = subjectNumber;
		this.startDate = startDate;
	}
	
	private void addEncounter(Encounter encounter) {
		encounters.add(encounter);
	}
	
	private void removeEncounter(Encounter encounter) {
		
	}
	
	private String getSubjectNumber() {
		return this.subjectNumber;
	}
	
	private void setSubjectNumber(String subjectNumber) {
		this.subjectNumber = subjectNumber;
	}
	
	
	
}
