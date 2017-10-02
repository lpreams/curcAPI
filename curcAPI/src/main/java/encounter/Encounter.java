package encounter;
import java.util.Date;

import subject.Subject;
import visit.Visit;

public class Encounter {
	
	private final int encounterID;
	private final Subject subject;
	private final Visit visit;
	private Date DOS;
	private boolean isPaid;
	private double paidAmount;
	
	public Encounter(int encounterID, Subject subject, Visit visit, Date DOS) {
	
		this.encounterID = encounterID;
		this.subject = subject;
		this.visit = visit;
		this.DOS = DOS;
		isPaid = false;
		paidAmount = 0;
		
	}
	
	private int getEncounterID() {
		return encounterID;
	}
	
	private Subject getSubject() {
		return subject;
	}
	
	private Visit getVisit() {
		return visit;
	}
	
	private void setDOS(Date DOS) {
		this.DOS = DOS;
	}
	
	private Date getDOS() {
		return DOS;
	}
	
	private void applyPayment(double pmtAmount) {
		paidAmount += pmtAmount;
		
	}


}
