package budget;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import javax.naming.NamingException;

import database.DatabaseAccess;

import database.DatabaseAccess;

public class BudgetFacade {
	
	private static BudgetFacade singleton;
	private DatabaseAccess bao;

	
	public BudgetFacade() {
		try { this.bao = DatabaseAccess.getInstance(); }
		catch(NamingException | SQLException e) { e.printStackTrace(); }
	}
	
	
	public static BudgetFacade getInstance() {
		if(singleton==null) { singleton = new BudgetFacade(); }
		return singleton;
	}
	
	
	public ArrayList<Budget> getBudgetByID(int id) {
		try { Class.forName("org.h2.Driver"); }
		catch(ClassNotFoundException e) { e.printStackTrace(); }
		ArrayList<Budget> budgetList = new ArrayList<Budget>();
		
		try { 
			Connection con = bao.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM budget WHERE budgetID=?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int budgetID = rs.getInt("budgetID");
				int studyID = rs.getInt("studyID");
				int version = rs.getInt("version");
				java.sql.Date effectiveDate = rs.getDate("effectiveDate");
				Budget budget = new Budget(budgetID, studyID, version, effectiveDate);
				budgetList.add(budget);	} } 
		catch(SQLException e) { e.printStackTrace(); }
		return budgetList;
	}
	
	
	
	public ArrayList<Budget> getBudgetByStudy(int study) {
		try { Class.forName("org.h2.Driver"); }
		catch(ClassNotFoundException e) { e.printStackTrace(); }
		ArrayList<Budget> budgetList = new ArrayList<Budget>();
		
		try { 
			Connection con = bao.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM budget WHERE studyID=?");
			stmt.setInt(1, study);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int budgetID = rs.getInt("budgetID");
				int studyID = rs.getInt("studyID");
				int version = rs.getInt("version");
				java.sql.Date effectiveDate = rs.getDate("effectiveDate");
				Budget budget = new Budget(budgetID, studyID, version, effectiveDate);
				budgetList.add(budget);	} } 
		catch(SQLException e) { e.printStackTrace(); }
		return budgetList;
	}

	
	public Budget createBudget(Budget budget) {
		try { Class.forName("org.h2.Driver"); } 
		catch(ClassNotFoundException e) { e.printStackTrace(); }
		
		Budget newBudget=null;
		
		try {
			Connection con = bao.getConnection();
			PreparedStatement createStmt = con.prepareStatement
				("INSERT INTO budget (studyID, version, effectiveDate) VALUES (?, ?, ?");
			createStmt.setInt(1, budget.getStudyID());
			createStmt.setInt(2, budget.getVersion());
			createStmt.setDate(3, budget.getEffectiveDate());
			int res = createStmt.executeUpdate();
			
			if(res==1) {
				PreparedStatement retrieveStmt = con.prepareStatement
					("SELECT * FROM budget WHERE studyID=? AND version=? AND effectiveDate=?");
				retrieveStmt.setInt(1, budget.getStudyID());
				retrieveStmt.setInt(2, budget.getVersion());
				retrieveStmt.setDate(3, budget.getEffectiveDate());
				ResultSet rs = retrieveStmt.executeQuery();
				
				while(rs.next()) {
					int budgetID = rs.getInt("budgetID");
					int studyID = rs.getInt("studyID");
					int version = rs.getInt("version");
					java.sql.Date effectiveDate = rs.getDate("effectiveDate");
					newBudget = new Budget(budgetID, studyID, version, effectiveDate); } } }
		catch(SQLException e) { e.printStackTrace(); }
		return newBudget;
	}
	
	
	
	
	
	
	
	
	
	
	
}
