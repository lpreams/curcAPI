package patient;
import java.sql.Connection;
import database.DatabaseAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import javax.naming.NamingException;

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
	
	public ArrayList<Patient> getPatients() throws SQLException {
		Connection con = pao.getConnection();
		PreparedStatement stmt = con.prepareStatement("SELECT id, fName, lName, mName, birth, death, sex, prov_id from clinic"); //insert stmt to grab pt info when db is set up
		ResultSet rs = stmt.executeQuery();
		
		ArrayList<Patient> patientArray = new ArrayList<Patient>();
		
		int count = 0;
		while(rs.next()) {
			int pt_id = rs.getInt("id");
			String fName = rs.getString("fName");
			String lName = rs.getString("lName");
			String mName = rs.getString("mName");
			Date birth = rs.getDate("birth");
			Date death = rs.getDate("death");
			String gender = rs.getString("sex"); //change sex to varchar(1) in db
			int prov_id = rs.getInt("prov_id");
			Patient patient = new Patient(pt_id, fName, mName, lName, birth, death, gender, prov_id);
			patientArray.add(patient);
			count++;		
		}
		
		if(count>0) {
			return patientArray;
		}
		else{
			return null;
		}

	}

	

	public ArrayList<Patient> getPatientByName(String firstName, String lastName) throws SQLException, ClassNotFoundException {
		
		Connection con = pao.getConnection();
		
		PreparedStatement stmt = con.prepareStatement("SELECT id, fName, lName, mName, birth, sex, prov_id FROM patient WHERE fName=? AND lName=?"); //insert statement to grab patient by name here
				stmt.setString(1, firstName); 
				stmt.setString(2, lastName);
				ResultSet rs = stmt.executeQuery();
				
				ArrayList<Patient> patientArray = new ArrayList<Patient>(); //fix the size of that array...
				int count = 0;
				while(rs.next()) {
					int pt_id = rs.getInt("id");
					String fName = rs.getString("fName");
					String lName = rs.getString("lName");
					String mName = rs.getString("mName");
					Date birth = rs.getDate("birth");
					Date death = rs.getDate("death");
					String gender = rs.getString("sex"); //change sex to varchar(1) in db
					int prov_id = rs.getInt("prov_id");
					Patient patient = new Patient(pt_id, fName, mName, lName, birth, death, gender, prov_id);
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
	
	ArrayList<Patient> createPatient(Patient patient) throws SQLException, ClassNotFoundException{
		
		Connection con = pao.getConnection();
		
		int pt_id = patient.getID();
		String fName = patient.getfName();
		String lName = patient.getlName();
		String mName = patient.getmName();
		Date bDay = (Date)patient.getbDay();
		Date dDay = patient.getbDay();
		String gender = patient.getGender();
		int prov_id = patient.getProviderID();
		
		PreparedStatement createStmt = con.prepareStatement("INSERT INTO patient (pt_id, fName, lName, mName, birth, death, sex, prov_id) VALUES (?,?,?,?,?,?,?,?");
		createStmt.setInt(1, pt_id);
		createStmt.setString(2, fName);
		createStmt.setString(3, lName);
		createStmt.setString(4, mName);
		createStmt.setDate(5, bDay);
		createStmt.setDate(6, dDay);
		createStmt.setString(7, gender);
		createStmt.setInt(8, prov_id);
	
		int res = createStmt.executeUpdate();
		
		if(res==1) {
			PreparedStatement retrieveStmt = con.prepareStatement("Select * from patient where pt_id=? AND fName=? AND lName=? AND mName=? AND birth=? AND death=? AND sex=? AND prov_id=?");
			retrieveStmt.setInt(1, pt_id);
			retrieveStmt.setString(2, fName);
			retrieveStmt.setString(3, lName);
			retrieveStmt.setString(4, mName);
			retrieveStmt.setDate(5, bDay);
			retrieveStmt.setDate(6, dDay);
			retrieveStmt.setString(7, gender);
			retrieveStmt.setInt(8, prov_id);
			ResultSet rs = retrieveStmt.executeQuery();
			
			ArrayList<Patient> patientArray = new ArrayList<Patient>();
			
			int count = 0;
			while(rs.next()) {
				int new_id = rs.getInt("id");
				String new_fName = rs.getString("fName");
				String new_lName = rs.getString("lName");
				String new_mName = rs.getString("mName");
				Date new_bDay = rs.getDate("birth");
				Date new_dDay = rs.getDate("death");
				String new_gender = rs.getString("sex");
				int new_provider = rs.getInt("provider");
				
				Patient newPatient = new Patient(new_id, new_fName, new_mName, new_lName, new_bDay, new_dDay, new_gender, new_provider);
				patientArray.add(patient);			
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

	




