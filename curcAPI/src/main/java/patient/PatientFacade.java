package patient;
import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import database.DatabaseAccess;

public class PatientFacade {
	
	private static PatientFacade singleton;
	private DatabaseAccess pao;
	
	
	public PatientFacade() {
		try { this.pao = DatabaseAccess.getInstance(); }
		catch (NamingException | SQLException e) { e.printStackTrace(); }
	}
	
	
	public static PatientFacade getInstance() {
		if(singleton==null) { singleton = new PatientFacade(); }
		return singleton;
	}
	
	
	public ArrayList<Patient> getPatientByName(String lastName, String firstName) {
		try { Class.forName("org.h2.Driver"); } 
		catch (ClassNotFoundException e) {	e.printStackTrace(); }

		ArrayList<Patient> patientArray = new ArrayList<Patient>();
		try {
			Connection con = pao.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM patient WHERE ptLName LIKE ? AND ptFName LIKE ?"); 
			stmt.setString(1, firstName + "%"); 
			stmt.setString(2, lastName + "%");
			ResultSet rs = stmt.executeQuery();
				
			while(rs.next()) {
				int pt_id = rs.getInt("ptID");
				String lName = rs.getString("ptLName");
				String fName = rs.getString("ptFName");
				String mName = rs.getString("ptMName");
				java.sql.Date birth = rs.getDate("ptDOB");
				java.sql.Date death = rs.getDate("ptDOD");
				int provID = rs.getInt("provID");
				String gender = rs.getString("ptGender");
				Patient patient = new Patient(pt_id, lName, fName, mName, birth, death, provID, gender);
				patientArray.add(patient); }
			con.close(); }
		catch(SQLException e) {	e.printStackTrace(); } 
		return patientArray;
	}

	
	public Patient getPatientByID(int id) {
		try{ Class.forName("org.h2.Driver"); } 
		catch(ClassNotFoundException e) { e.printStackTrace(); }
		
		Patient patient = null;
		
		try{
			Connection con = pao.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM patient WHERE ptID=?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
		
			while(rs.next()) {
				int pt_id = rs.getInt("ptID");
				String lName = rs.getString("ptLName");
				String fName = rs.getString("ptFName");
				String mName = rs.getString("ptMName");
				java.sql.Date birth = rs.getDate("ptDOB");
				java.sql.Date death = rs.getDate("ptDOD");
				int provID = rs.getInt("provID");
				String gender = rs.getString("ptGender");
				patient = new Patient(pt_id, lName, fName, mName, birth, death, provID, gender); } }
		catch(SQLException e) { e.printStackTrace(); }
		return patient;
	}
	
	
	public Patient createPatient(Patient patient) {
		try{ Class.forName("org.h2.Driver"); }
		catch(ClassNotFoundException e) { e.printStackTrace(); }
			
		Connection con=null;
		Patient newPatient = null;
		
		try{
			con = pao.getConnection();
			PreparedStatement createStmt = con.prepareStatement
					("INSERT INTO patient (ptLName, ptFName, ptMName, ptDOB, provID, ptGender) VALUES (?,?,?,?,?,?)");
			createStmt.setString(1, patient.getlName());
			createStmt.setString(2, patient.getfName());
			createStmt.setString(3, patient.getmName());
			createStmt.setDate(4, patient.getbDay());
			createStmt.setInt(5, patient.getprovID());
			createStmt.setString(6, patient.getGender());
			int res = createStmt.executeUpdate();		
	
			if(res==1) {
				PreparedStatement retrieveStmt = con.prepareStatement("SELECT * FROM patient WHERE ptLName=? AND ptFName=? AND ptMName=? AND ptDOB=? AND provID=? AND ptGender=?");
				retrieveStmt.setString(1, patient.getlName());
				retrieveStmt.setString(2, patient.getfName());
				retrieveStmt.setString(3, patient.getmName());
				retrieveStmt.setDate(4, patient.getbDay());
				retrieveStmt.setInt(5, patient.getprovID());
				retrieveStmt.setString(6, patient.getGender());
			
				ResultSet rs = retrieveStmt.executeQuery();
				
				while(rs.next()) {
					int id = rs.getInt("ptID");
					String lName = rs.getString("ptLName");
					String fName = rs.getString("ptFName");
					String mName = rs.getString("ptMName");
					java.sql.Date bDay = rs.getDate("ptDOB");
					java.sql.Date dDay = rs.getDate("ptDOD");
					int provID = rs.getInt("provID");
					String gender = rs.getString("ptGender");
									
					newPatient = new Patient(id, lName, fName, mName, bDay, dDay, provID, gender); } } }
		catch(SQLException e) {	e.printStackTrace(); }
		return newPatient;
	}

	
	public Patient updatePatientlName(int ptID, String newlName) {
		try{ Class.forName("org.h2.Driver"); } 
		catch(ClassNotFoundException e) { e.printStackTrace(); }
			
		Connection con=null;
		int res=0;
		Patient updatedPatient = null;
		try{
			con = pao.getConnection();
			PreparedStatement updateStmt = con.prepareStatement("UPDATE patient SET fName=? WHERE ptID=?");
			updateStmt.setString(1, newlName);
			updateStmt.setInt(2, ptID);
			res=updateStmt.executeUpdate();
			
			if(res==1) {
				PreparedStatement retrieveStmt = con.prepareStatement("SELECT * FROM patient WHERE ptID=?");
				retrieveStmt.setInt(1, ptID);
			
				ResultSet rs = retrieveStmt.executeQuery();
				
				while(rs.next()) {
					int id = rs.getInt("ptID");
					String lName = rs.getString("ptLName");
					String fName = rs.getString("ptFName");
					String mName = rs.getString("ptMName");
					Date bDay = rs.getDate("ptDOB");
					Date dDay = rs.getDate("ptDOD");
					int provID = rs.getInt("provID");
					String gender = rs.getString("ptGender");
					updatedPatient = new Patient(id, lName, fName, mName, bDay, dDay, provID, gender); } } }
		catch(SQLException e) { e.printStackTrace(); }
		return updatedPatient;		
	}
	

	public Patient updatePatientfName(int ptID, String newfName) {
		try{ Class.forName("org.h2.Driver"); } 
		catch(ClassNotFoundException e) { e.printStackTrace(); }
			
		Connection con=null;
		int res=0;
		Patient updatedPatient = null;
		try{
			con = pao.getConnection();
			PreparedStatement updateStmt = con.prepareStatement("UPDATE patient SET fName=? WHERE ptID=?");
			updateStmt.setString(1, newfName);
			updateStmt.setInt(2, ptID);
			res=updateStmt.executeUpdate();
			
			if(res==1) {
				PreparedStatement retrieveStmt = con.prepareStatement("SELECT * FROM patient WHERE ptID=?");
				retrieveStmt.setInt(1, ptID);
				ResultSet rs = retrieveStmt.executeQuery();
				
				while(rs.next()) {
					int id = rs.getInt("ptID");
					String lName = rs.getString("ptLName");
					String fName = rs.getString("ptFName");
					String mName = rs.getString("ptMName");
					java.sql.Date bDay = rs.getDate("ptDOB");
					java.sql.Date dDay = rs.getDate("ptDOD");
					int provID = rs.getInt("provID");
					String gender = rs.getString("ptGender");		
					updatedPatient = new Patient(id, lName, fName, mName, bDay, dDay, provID, gender); } } }
		catch(SQLException e) { e.printStackTrace(); }	
		return updatedPatient;		
	}
	
	
	public Patient updatePatientmName(Patient pt, String newmName) {
		try{ Class.forName("org.h2.Driver"); } 
		catch(ClassNotFoundException e) { e.printStackTrace(); }

		Patient updatedPatient = null;
		try{
			Connection con = pao.getConnection();
			PreparedStatement updateStmt = con.prepareStatement("UPDATE patient SET fName=? WHERE ptID=?");
			updateStmt.setString(1, newmName);
			updateStmt.setInt(2, pt.getID());
			int res=updateStmt.executeUpdate();
			
			if(res==1) {
				PreparedStatement retrieveStmt = con.prepareStatement("SELECT * FROM patient WHERE ptID=?");
				retrieveStmt.setInt(1, pt.getID());
			
				ResultSet rs = retrieveStmt.executeQuery();
				
				while(rs.next()) {
					int id = rs.getInt("ptID");
					String lName = rs.getString("ptLName");
					String fName = rs.getString("ptFName");
					String mName = rs.getString("ptMName");
					java.sql.Date bDay = rs.getDate("ptDOB");
					java.sql.Date dDay = rs.getDate("ptDOD");
					int provID = rs.getInt("provID");
					String gender = rs.getString("ptGender");		
					updatedPatient = new Patient(id, lName, fName, mName, bDay, dDay, provID, gender); } } }
		catch(SQLException e) { e.printStackTrace(); }
		return updatedPatient;		
	}

}


