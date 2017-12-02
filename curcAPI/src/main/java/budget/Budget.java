package budget;
import java.util.ArrayList;
import java.sql.Date;
import java.text.SimpleDateFormat;

import studyVisit.StudyVisit;

public class Budget {
	
	private int budgetID;
	private int studyID;
	private int version;
	private static ArrayList<StudyVisit> visits;
	private java.sql.Date effectiveDate;
	private java.sql.Date endDate;
	
	private transient SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
	
	private Budget(int studyID, int version, String effective){
		this.studyID = studyID;
		try {
			java.util.Date parsed = simpleDate.parse(effective);
			this.effectiveDate = new java.sql.Date(parsed.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}	
		this.effectiveDate = effectiveDate;	
	}
	
	Budget(int budgetID, int studyID, int version, java.sql.Date effective){
		this.budgetID = budgetID;
		this.studyID = studyID;		
		this.effectiveDate = effective;
	}
	
	private int getBudgetID() { return budgetID; }
	
	int getStudyID() { return studyID; }
	private void setStudyID(int studyID) { this.studyID = studyID; }
		
	int getVersion() { return version; }
	private void setVersion(int version) { this.version = version; }
	
	java.sql.Date getEffectiveDate() { return effectiveDate; }
	private void setEffective(java.sql.Date start) { this.effectiveDate = start; }
	
	private Date getEndDate() { return endDate; }
	private void setEndDate(java.sql.Date end) { this.endDate = end; }
	 
}
