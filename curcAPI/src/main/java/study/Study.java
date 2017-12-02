package study;
import java.util.ArrayList;
import java.util.Date;
//inherit Protocol

import budget.Budget;
import coordinator.Coordinator;
import sponsor.Sponsor;
import studyVisit.StudyVisit;
import subject.Subject;
import studyVisit.StudyVisit;

public class Study {
	
	private int studyID;
	private String protocol;
	private int sponsorID;
	private String name;
	private int statusCode;
	private String status;
	private int coordinator;
	private static ArrayList<Study> studyList;

	
	private Study(String protocol, int sponsorID, String name, int statusCode) {
		this.protocol = protocol;
		this.sponsorID = sponsorID;
		this.name = name;
		this.statusCode = statusCode;
	}
	
	
	
	/**
	 * A class for representing a study object 
	 * that has been returned from the database
	 * @param studyID StudyID (primary key in database)
	 * @param protocol The study protocol number
	 * @param sponsor The study sponsor
	 * @param name The study nickname
	 * @param status The study status (0=upcoming, 1=open to enrollment, 2=closed to enrollment, 3=closed)
	 */
	Study(int studyID, String protocol, int sponsorID, String name, String status) {
		this.studyID = studyID;
		this.protocol = protocol;
		this.sponsorID = sponsorID;
		this.name = name;
		this.status = status;		
	}
	
	
	private Study(int studyID, String protocol, int sponsorID, String name, String status, int coordinator) {
		this.studyID = studyID;
		this.protocol = protocol;
		this.sponsorID = sponsorID;
		this.name = name;
		this.status = status;		
	}
	
	
	public int getStudyID() { return this.studyID; }
	String getProtocol() { return protocol; }
	
	int getSponsorID() { return this.sponsorID; }	
	private void setSponsor(int sponsorID) { this.sponsorID = sponsorID; }
	
	String getStudyName() { return this.name; }
	private void setStudyName(String studyName) { this.name = name; }
	
	int getStatusCode() { return this.statusCode; }
	private void setStatusCode(int status) { this.statusCode = statusCode; }
	
	private String getStatus() { return this.status; }
	
	private int getCoordinator() { return this.coordinator; }
	private void setCoordinator(int coordinator) { 	this.coordinator = coordinator; }
	
	
}
