package patient;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.DriverManager;

import javax.naming.NamingException;

import database.DatabaseAccess;

public class PatientFacade {
	
	private static PatientFacade singleton;
	private DatabaseAccess pao;
	
	private PatientFacade() throws NamingException, SQLException {
		this.pao = DatabaseAccess.getInstance();
	}
	
	public static PatientFacade getInstance() throws NamingException, SQLException {
		if(singleton==null) {
			singleton = new PatientFacade();
		}
		return singleton;
	}
	
	
	public ArrayList<Patient> getPatientByName(String lastName, String firstName) throws SQLException, ClassNotFoundException {
		
		Class.forName("org.h2.Driver");
		Connection con = pao.getConnection();
		
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM patient WHERE ptLName LIKE ? AND ptFName LIKE ?"); 
		stmt.setString(1, firstName + "%"); 
		stmt.setString(2, lastName + "%");
		ResultSet rs = stmt.executeQuery();
				
		ArrayList<Patient> patientArray = new ArrayList<Patient>(); 
		int count = 0;
		while(rs.next()) {
			int pt_id = rs.getInt("ptID");
			String lName = rs.getString("ptLName");
			String fName = rs.getString("ptFName");
			String mName = rs.getString("ptMName");
			Date birth = rs.getDate("ptDOB");
			Date death = rs.getDate("ptDOD");
			int provID = rs.getInt("provID");
			String gender = rs.getString("ptGender");
			Patient patient = new Patient(pt_id, lName, fName, mName, birth, death, provID, gender);
			patientArray.add(patient);
			count++;		
		}
		
		if(count>0) {
			return patientArray;
		}
		else {
			return null;
		}
	}
	
	
	public ArrayList<Patient> getPatientByID(int id) throws SQLException, ClassNotFoundException{
		
		Class.forName("org.h2.Driver");
		Connection con = pao.getConnection();
		
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM patient WHERE ptID=?");
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		
		ArrayList<Patient> patientArray = new ArrayList<Patient>();
		int count = 0;
		while(rs.next()) {
			int pt_id = rs.getInt("ptID");
			String lName = rs.getString("ptLName");
			String fName = rs.getString("ptFName");
			String mName = rs.getString("ptMName");
			Date birth = rs.getDate("ptDOB");
			Date death = rs.getDate("ptDOD");
			int provID = rs.getInt("provID");
			String gender = rs.getString("ptGender");
			Patient patient = new Patient(pt_id, lName, fName, mName, birth, death, provID, gender);
			patientArray.add(patient);
			count++;		
		}
		if(count>0) {
			return patientArray;
		}
		else {
			return null;
		}
	}
	
	
	public ArrayList<Patient> createPatient(Patient patient) throws SQLException, ClassNotFoundException{
		
		Connection con = pao.getConnection();
		Class.forName("org.h2.Driver");
		
		String lName = patient.getlName();
		String fName = patient.getfName();
		String mName = patient.getmName();
		Date bDay = patient.getbDay();
		Date dDay = patient.getdDay();
		int provID = patient.getprovID();
		String gender = patient.getGender();
		
		PreparedStatement createStmt = con.prepareStatement
				("INSERT INTO patient (ptLName, ptFName, ptMName, ptDOB, ptDOD, provID, ptGender) VALUES (?,?,?,?,?,?,?)");
		createStmt.setString(1, fName);
		createStmt.setString(2, lName);
		createStmt.setString(3, mName);
		createStmt.setDate(4, (java.sql.Date) bDay);
		createStmt.setDate(5, (java.sql.Date) dDay);
		createStmt.setInt(6, provID);
		createStmt.setString(7, gender);
	
		int res = createStmt.executeUpdate();
				
		if(res==1) {
			PreparedStatement retrieveStmt = con.prepareStatement
					("SELECT * FROM patient WHERE ptLName=? AND ptFName=? AND ptMName=? AND ptDOB=? AND ptDOD=? AND provID=? AND ptGender=?");
			retrieveStmt.setString(1, lName);
			retrieveStmt.setString(2, fName);
			retrieveStmt.setString(3, mName);
			retrieveStmt.setDate(4, (java.sql.Date) bDay);
			retrieveStmt.setDate(5, (java.sql.Date) dDay);
			retrieveStmt.setInt(6, provID);
			retrieveStmt.setString(7, gender);
			
			ResultSet rs = retrieveStmt.executeQuery();
			
			ArrayList<Patient> patientArray = new ArrayList<Patient>();
			
			int count = 0;
			while(rs.next()) {
				int new_id = rs.getInt("ptID");
				String new_lName = rs.getString("ptLName");
				String new_fName = rs.getString("ptFName");
				String new_mName = rs.getString("ptMName");
				Date new_bDay = rs.getDate("ptDOB");
				Date new_dDay = rs.getDate("ptDOD");
				int new_provID = rs.getInt("provID");
				String new_gender = rs.getString("ptGender");
				
				Patient newPatient = new Patient(new_id, new_lName, new_fName, new_mName, new_bDay, new_dDay, new_provID, new_gender);
				patientArray.add(newPatient);			
				count++;
			}
			
			if(count>0) {
				return patientArray;
			}
			else {
				return null;
			}			
		}
		else {
			return null;
		}	
	}

}

	




