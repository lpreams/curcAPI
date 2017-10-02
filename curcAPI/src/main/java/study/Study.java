package study;
import java.util.ArrayList;
import java.util.Date;
//inherit Protocol

import budget.Budget;
import coordinator.Coordinator;
import sponsor.Sponsor;
import subject.Subject;
import visit.Visit;

public class Study {
	
	private final int studyID;
	private final String protocol;
	private Sponsor sponsor;
	private String name;
	private String status;
	private Date ote = null;
	private Date cte = null;
	private Date closed = null;
	private ArrayList<Subject> subjects;
	private ArrayList<Visit> visits;
	private ArrayList<Budget> budgets;
	private Coordinator coordinator;
	private static ArrayList<Study> studyList;

	
	private Study(int studyID, String protocol, Sponsor sponsor, String name, String status) {
		
		this.studyID = studyID;
		this.protocol = protocol;
		this.sponsor = sponsor;
		this.name = name;
		this.status = status; //if not upcoming, prompt for ote/cte/closed dates?
		
		subjects = new ArrayList<>();
		visits = new ArrayList<>();
		budgets = new ArrayList<>();	
		
		Study.studyList.add(this);
		
	}
	
	private int getStudyID() {
		return this.studyID;
	}
	
	private String getProtocol() {
		return protocol;
	}
	
	private Sponsor getSponsor() {
		return sponsor;
	}
	
	private void setSponsor(Sponsor sponsor) {
		this.sponsor = sponsor;
	}
	
	private int setStatus(String status, Date date) {

		if(status.equalsIgnoreCase("upcoming")) {
			this.status = status;
			//do something here
			return 1;
		}else if(status.equalsIgnoreCase("ote")) {
			this.status = status;
			ote = date;
			return 1;
		}else if(status.equalsIgnoreCase("cte")) {
			this.status = status;
			cte = date;
			return 1;
		}else if(status.equalsIgnoreCase("closed")) {
			this.status = status;
			closed = date;
			return 1;
		}
		
		//else return some error code I guess?
		return 0;
	}
	
	private String getStatus() {		
		if(status.equalsIgnoreCase("upcoming")||status.equalsIgnoreCase("ote")||status.equalsIgnoreCase("cte") || status.equalsIgnoreCase("closed")){
			return status;
		} else {
			return "Status not set";
		}
	}
	
	private int addSubject(Subject subject) {
		//make sure subject isn't already in the list
		int flag = subjects.indexOf(subject);
		if(flag==-1) {
			subjects.add(subject);
			return 1;
		} else{
			//return some error code
			return 0;
		}
	}
	
	private Subject getSubject(Subject subject) {
		int index = subjects.indexOf(subject);
		return subjects.get(index);
	}
	

	private int removeSubject(Subject subject) {
		int index = subjects.indexOf(subject);
		if(index==-1){
			return 0;
		} else{
			subjects.remove(index);
			return 1;
		}
	}

	private int addBudget(Budget budget) {
	//search for budget in list -- maybe by effective date or version?
		budgets.add(budget);
		return 1;	
	}
	
	private Budget getBudget(Budget budget) {
		//search for budget in array list
		int index = budgets.indexOf(budget);
		return budgets.get(index);
	}
	
	private int setCoordinator(Coordinator coordinator) {
		this.coordinator = coordinator;
		return 1;
	}
	
	
}
