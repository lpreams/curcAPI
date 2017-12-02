package subject;
import java.util.ArrayList;
import java.sql.Date;
import java.text.SimpleDateFormat;

import study.Study;
import subjectVisit.SubjectVisit;

//inherit Patient and Study / Protocol

public class Subject {

	private int subjectID;
	private int ptID;
	private int studyID;
	private String subjectNumber;
	private java.sql.Date startDate;
	private java.sql.Date stopDate=null;
	private transient SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
	
	public Subject(int ptID, int studyID, String subjectNumber, java.sql.Date start, java.sql.Date stop) {
		this.ptID = ptID;
		this.studyID = studyID;
		this.subjectNumber = subjectNumber;
		this.startDate = start;
		this.stopDate = stop;
	}
	
	Subject(int subjectID, int ptID, int studyID, String subjectNumber, java.sql.Date start, java.sql.Date stop) {
		this.subjectID = subjectID;
		this.ptID = ptID;
		this.studyID = studyID;
		this.subjectNumber = subjectNumber;
		this.startDate = start;
		this.stopDate = stop;
	}
	
	private int getSubjectID() { return this.subjectID; }

	public int getPtID() { return this.ptID; }
	private void setPtID(int ptID) { this.ptID = ptID; }
	
	int getStudyID() { return this.studyID; }
	private void setStudyID(int studyID) { this.studyID = studyID; }
	
	String getSubjectNumber() {	return this.subjectNumber; }
	private void setSubjectNumber(String subjectNumber) { this.subjectNumber = subjectNumber; }
	
	java.sql.Date getStartDate() { return this.startDate;}
	private void setStartDate(java.sql.Date startDate) { this.startDate = startDate; }
	
	private java.sql.Date getStopDate() { return this.stopDate; }
	private void setStopDate(java.sql.Date stopDate) { this.stopDate = stopDate; }
	
	
}
