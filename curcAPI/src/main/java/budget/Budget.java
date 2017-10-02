package budget;
import java.util.ArrayList;
import java.util.Date;

import study.Study;
import visit.Visit;

public class Budget {
	private final Study study;
	private int version;
	private static ArrayList<Visit> visits;
	private Date startDate;
	private Date stopDate;
	
	private Budget(int studyID, Study study, Date startDate){
		this.study = study;
		visits = new ArrayList<>();		
	}
	
	private Study getStudy() {
		return this.study;
	}

	private void removeVisit(int visitID) {
		//find visit in list and remove it
	}
	
	private double getVisitCost(int visitID) {
		Visit visit = null;
		double cost = 0;
		
		return cost;
	}	
	
	private int addVisit(Visit visit) {
		
		int index = visits.indexOf(visit);
		if(index==-1) {
			visits.add(visit);
			return 1;
		} else {
			return 0;
			//some warning
		}
	}
	
	private int removeVisit(Visit visit) {
		int index = visits.indexOf(visit);
		if(index==-1) {
			//error
			return 0;
		} else {
			visits.remove(index);
			return 1;
		}
	}
	

	 
}
