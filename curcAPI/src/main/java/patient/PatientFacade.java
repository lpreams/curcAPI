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
		try {
			this.pao = DatabaseAccess.getInstance();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static PatientFacade getInstance() {
		if(singleton==null) {
			singleton = new PatientFacade();
		}
		return singleton;
	}
	
	
	public ArrayList<Patient> getPatientByName(String lastName, String firstName) {
		
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		ArrayList<Patient> patientArray = new ArrayList<Patient>(); 
		int count = 0;
		
		try{
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
				patientArray.add(patient);
				count++;		
			}
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		if(count>0) {
			return patientArray;
		}
		else {
			return null;
		}
	}

	
	public Patient getPatientByID(int id) {
		
		try{
			Class.forName("org.h2.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Patient patient = null;
		int count = 0;
		
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
				patient = new Patient(pt_id, lName, fName, mName, birth, death, provID, gender);
				count++;		
			}
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		if(count>0) {
			return patient;
		}
		else {
			return null;
		}
	}
	
	
	public Patient createPatient(Patient patient) {
		
		try{
			Class.forName("org.h2.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String lName = patient.getlName();
		String fName = patient.getfName();
		String mName = patient.getmName();
		java.sql.Date bDay = patient.getbDay();
		int provID = patient.getprovID();
		String gender = patient.getGender();
		
		Connection con = null;
		int res = 0;
		
		try{
			con = pao.getConnection();
			PreparedStatement createStmt = con.prepareStatement
					("INSERT INTO patient (ptLName, ptFName, ptMName, ptDOB, provID, ptGender) VALUES (?,?,?,?,?,?)");
			createStmt.setString(1, lName);
			createStmt.setString(2, fName);
			createStmt.setString(3, mName);
			createStmt.setDate(4, bDay);
			createStmt.setInt(5, provID);
			createStmt.setString(6, gender);
			res = createStmt.executeUpdate();
			con.commit();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		if(res==1) {
			Patient newPatient = null;
			int count = 0;
			try{
				PreparedStatement retrieveStmt = con.prepareStatement
						("SELECT * FROM patient WHERE ptLName=? AND ptFName=? AND ptMName=? AND ptDOB=? AND provID=? AND ptGender=?");
				retrieveStmt.setString(1, lName);
				retrieveStmt.setString(2, fName);
				retrieveStmt.setString(3, mName);
				retrieveStmt.setDate(4, (java.sql.Date) bDay);
				retrieveStmt.setInt(5, provID);
				retrieveStmt.setString(6, gender);
			
				ResultSet rs = retrieveStmt.executeQuery();
			
				while(rs.next()) {
					int new_id = rs.getInt("ptID");
					String new_lName = rs.getString("ptLName");
					String new_fName = rs.getString("ptFName");
					String new_mName = rs.getString("ptMName");
					Date new_bDay = rs.getDate("ptDOB");
					int new_provID = rs.getInt("provID");
					String new_gender = rs.getString("ptGender");
				
					newPatient = new Patient(new_id, new_lName, new_fName, new_mName, new_bDay, new_provID, new_gender);			
					count++;
				}
				System.out.printf("COUNT: %d\n", count);
				con.close();
			}catch(SQLException e) {
					e.printStackTrace();
			}
			
			if(count>0) {
				System.out.println("New patient created!");
				return newPatient;
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


