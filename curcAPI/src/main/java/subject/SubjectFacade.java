package subject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import javax.naming.NamingException;

import database.DatabaseAccess;

public class SubjectFacade {
	
	private static SubjectFacade singleton;
	private DatabaseAccess sao;
	
	public SubjectFacade() {
		try { this.sao = DatabaseAccess.getInstance(); }
		catch(NamingException | SQLException e) { e.printStackTrace(); }
	}
	
	private SubjectFacade getInstance() {
		if(singleton==null) { singleton = new SubjectFacade(); }
		return singleton;
	}
	
	
	public ArrayList<Subject> getSubjectsByStudy(int study) {
		try { Class.forName("org.h2.Driver"); }
		catch(ClassNotFoundException e) { e.printStackTrace(); }
				
		ArrayList<Subject> subjectList = new ArrayList<Subject>();
		
		try {
			Connection con = sao.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM subject WHERE studyID=?");
			stmt.setInt(1, study);
			ResultSet rs = stmt.executeQuery();
		
			while(rs.next()) {
				int subjectID = rs.getInt("subID");
				int studyID = rs.getInt("studyID");
				String subjectNumber = rs.getString("subjectNum");
				int ptID = rs.getInt("ptID");
				java.sql.Date startDate = rs.getDate("subStart");
				java.sql.Date stopDate = rs.getDate("subStop");
				Subject subject = new Subject(subjectID, ptID, studyID, subjectNumber, startDate, stopDate);
				subjectList.add(subject); } }
		catch(SQLException e) { e.printStackTrace(); }	
		return subjectList;
	}
	
	
	public ArrayList<Subject> getSubjectsByPatientID(int id)	{
		try { Class.forName("org.h2.Driver"); }
		catch(ClassNotFoundException e) {e.printStackTrace(); }
		ArrayList<Subject> subjectList = new ArrayList<Subject>();
		
		try {
			Connection con = sao.getConnection();
			PreparedStatement stmt = con.prepareStatement("Select * from subject, patient WHERE subject.ptID = patient.ptID AND patient.ptID = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int subjectID = rs.getInt("subID");
				int studyID = rs.getInt("studyID");
				String subjectNumber = rs.getString("subjectNum");
				int ptID = rs.getInt("ptID");
				java.sql.Date startDate = rs.getDate("subStart");
				java.sql.Date stopDate = rs.getDate("subStop");
				Subject subject = new Subject(subjectID, ptID, studyID, subjectNumber, startDate, stopDate);
				subjectList.add(subject); } }
		catch(SQLException e) { e.printStackTrace(); }		
		return subjectList;
	}
	
	
	public Subject createSubject(Subject subject) {
		try { Class.forName("org.h2.Driver"); }
		catch(ClassNotFoundException e) { e.printStackTrace(); }
		
		Subject newSubject=null;
		
		try {
			Connection con=sao.getConnection();
			PreparedStatement create = con.prepareStatement
				("INSERT INTO subject (subjectnum, studyID, ptID, subStart) VALUES (?,?,?,?)");
			create.setString(1, subject.getSubjectNumber());
			create.setInt(2, subject.getStudyID());
			create.setInt(3, subject.getPtID());
			create.setDate(4, subject.getStartDate());
			int res = create.executeUpdate();
			
			if(res==1) {
				PreparedStatement retrieve = con.prepareStatement
					("SELECT * FROM subject WHERE subjectNum=? AND studyID=? AND ptID=? AND subStart=?");
				retrieve.setString(1, subject.getSubjectNumber());
				retrieve.setInt(2, subject.getStudyID());
				retrieve.setInt(3, subject.getPtID());
				retrieve.setDate(4, subject.getStartDate());
				ResultSet rs = retrieve.executeQuery();
				
				while(rs.next()) {
					int subjectID = rs.getInt("subID");
					String subjectNum = rs.getString("subjectNum");
					int studyID = rs.getInt("studyID");
					int ptID = rs.getInt("ptID");
					java.sql.Date start = rs.getDate("subStart");
					java.sql.Date stop = rs.getDate("subStop");
					newSubject = new Subject(subjectID, ptID, studyID, subjectNum, start, stop); } } }
		catch(SQLException e) { e.printStackTrace(); }		
		return newSubject;
	}
	

}
