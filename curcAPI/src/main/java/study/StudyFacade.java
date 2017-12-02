package study;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import javax.naming.NamingException;

import database.DatabaseAccess;

public class StudyFacade {

	private static StudyFacade singleton;
	private DatabaseAccess sao;
	
	
	public StudyFacade() {
		try { this.sao = DatabaseAccess.getInstance(); }
		catch(NamingException | SQLException e) { e.printStackTrace(); }		
	}
	
	
	public static StudyFacade getInstance() {
		if(singleton==null) { singleton = new StudyFacade(); }
		return singleton;
	}
	
	
	public ArrayList<Study> getStudies() {
		try { Class.forName("org.h2.Driver"); }
		catch(ClassNotFoundException e) { e.printStackTrace(); }
		
		ArrayList<Study> studyList = new ArrayList<Study>();
		
		try{
			Connection con = sao.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM study");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int studyID = rs.getInt("studyID");
				String protocol = rs.getString("protocol");
				int sponsorID = rs.getInt("sponsorID");
				String name = rs.getString("studyName");
				String status = rs.getString("studyStatus");
				Study study = new Study(studyID, protocol, sponsorID, name, status);
				studyList.add(study); }	}
		catch(SQLException e) { e.printStackTrace(); }
		return studyList;
	}
	
	
	public ArrayList<Study> getStudyByName(String studyName) {
		try { Class.forName("org.h2.Driver"); }
		catch(ClassNotFoundException e) { e.printStackTrace(); }
		
		ArrayList<Study> studyList = new ArrayList<Study>();
		
		try {
			Connection con = sao.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM study WHERE name LIKE ?");
			stmt.setString(1, studyName);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int studyID = rs.getInt("studyID");
				String protocol = rs.getString("protocol");
				int sponsorID = rs.getInt("sponsorID");
				String name = rs.getString("studyName");
				String status = rs.getString("studyStatus");
				Study study = new Study(studyID, protocol, sponsorID, name, status);
				studyList.add(study); } } 
		catch(SQLException e) { e.printStackTrace(); }		
		return studyList;
	}
	
	
	public Study getStudyByID(int id) {
		try { Class.forName("org.h2.Driver"); }
		catch(ClassNotFoundException e)	{ e.printStackTrace(); }
		
		Study study = null;
		
		try { 
			Connection con = sao.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM study WHERE studyID=?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int studyID = rs.getInt("studyID");
				String protocol = rs.getString("protocol");
				int sponsorID = rs.getInt("sponsorID");
				String studyName = rs.getString("studyName");
				String status = rs.getString("studyStatus");
				study = new Study(studyID, protocol, sponsorID, studyName, status); } } 
		catch(SQLException e) { e.printStackTrace(); }
		return study;
	}
	
	
	public ArrayList<Study> getStudyByStatus(int status) {
		try { Class.forName("org.h2.Driver"); }
		catch(ClassNotFoundException e) { e.printStackTrace(); }
		
		ArrayList<Study> studyList = new ArrayList<Study>();
		
		try {
			Connection con = sao.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM study WHERE studyStatus=?");
			stmt.setInt(1, status);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int studyID = rs.getInt("studyID");
				String protocol = rs.getString("protocol");
				int sponsorID = rs.getInt("sponsorID");
				String studyName = rs.getString("studyName");
				String studyStatus = rs.getString("studyStatus");
				Study study = new Study(studyID, protocol, sponsorID, studyName, studyStatus);
				studyList.add(study); }	} 
		catch(SQLException e) { e.printStackTrace(); }
		return studyList;
	}
	
	
	public ArrayList<Study> getStudyBySponsor(int sponsorID) {
		try { Class.forName("org.h2.Driver"); }
		catch(ClassNotFoundException e) { e.printStackTrace(); }
		
		ArrayList<Study> studyList = new ArrayList<Study>();
		
		try {
			Connection con = sao.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM study WHERE sponsorID=?");
			stmt.setInt(1, sponsorID);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int studyID = rs.getInt("studyID");
				String protocol = rs.getString("protocol");
				int sponsor = rs.getInt("sponsorID");
				String studyName = rs.getString("studyName");
				String studyStatus = rs.getString("studyStatus");
				Study study = new Study(studyID, protocol, sponsor, studyName, studyStatus);
				studyList.add(study); }	} 
		catch(SQLException e) { e.printStackTrace(); }
		return studyList;
	}
	
	
	public ArrayList<Study> getStudyByProtocol(String protocol) {
		try { Class.forName("org.h2.Driver"); }
		catch(ClassNotFoundException e) { e.printStackTrace(); }
		
		ArrayList<Study> studyList = new ArrayList<Study>();
		
		try {
			Connection con = sao.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM study WHERE protocol LIKE?");
			stmt.setString(1, protocol + "%");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int studyID = rs.getInt("studyID");
				String theprotocol = rs.getString("protocol");
				int sponsor = rs.getInt("sponsorID");
				String studyName = rs.getString("studyName");
				String studyStatus = rs.getString("studyStatus");
				Study study = new Study(studyID, theprotocol, sponsor, studyName, studyStatus);
				studyList.add(study); }	} 
		catch(SQLException e) { e.printStackTrace(); }
		return studyList;
	}
	
	
	public Study createStudy(Study study) {
		try { Class.forName("org.h2.Driver"); }
		catch(ClassNotFoundException e) { e.printStackTrace();}
		
		Connection con=null;
		int res=0;
		Study newStudy=null;
		
		try {
			con=sao.getConnection();
			PreparedStatement create = con.prepareStatement
				("INSERT INTO study (protocol, sponsorID, studyName, studyStatus) VALUES (?,?,?,?)");
			create.setString(1, study.getProtocol());
			create.setInt(2, study.getSponsorID());
			create.setString(3, study.getStudyName());
			create.setInt(4, study.getStatusCode());
			res = create.executeUpdate();
			
			if(res==1) {
				PreparedStatement retrieve = con.prepareStatement
					("SELECT * FROM study WHERE protocol=? AND sponsorID=? AND studyName=? AND studyStatus=?");
				retrieve.setString(1, study.getProtocol());
				retrieve.setInt(2, study.getSponsorID());
				retrieve.setString(3, study.getStudyName());
				retrieve.setInt(4, study.getStatusCode());
				ResultSet rs = retrieve.executeQuery();
				
				while(rs.next()) {
					int studyID = rs.getInt("studyID");
					String protocol = rs.getString("protocol");
					int sponsorID = rs.getInt("sponsorID");
					String studyName = rs.getString("studyName");
					String studyStatus = rs.getString("studyStatus");
					newStudy = new Study(studyID, protocol, sponsorID, studyName, studyStatus);	} } }
		catch(SQLException e) { e.printStackTrace(); }
		return newStudy;	
	}
	
	
}
