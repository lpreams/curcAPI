package patient;
import java.util.ArrayList;
import java.sql.Date;

/**
 * A class to represent a patient in the clinic
 * @author Alex
 *
 */
public class Patient {
	
	private int pt_id;
	private String fName;
	private String mName;
	private String lName;
	private Date bDay;
	private Date dDay = null;
	private String gender;
	private int providerID;
	private static ArrayList<Patient> patientList;
	
	/**
	 * Create a patient object containing information about a 
	 * patient that was returned from the database
	 * @param id The patient_id stored in the database as the primary key
	 * @param fName The patient first name
	 * @param mName The patient middle name
	 * @param lName The patient last name
	 * @param bDay The patient date of birth 
	 * @param dDay The patient date of death
	 * @param gender The patient gender
	 * @param providerID The patient 
	 */
	Patient(int id, String fName, String mName, String lName, Date bDay, Date dDay, String gender, int providerID) {
		this.pt_id = id;
		this.fName = fName;
		this.mName = mName;
		this.lName = lName; 
		this.bDay = bDay;
		this.dDay = dDay;
		this.gender = gender;
		this.providerID = providerID;
		Patient.patientList.add(this); 
		
	}
			
	/**
	 * A class to create a new patient in the clinic
	 * @param fName The patient first name
	 * @param mName The patient middle name
	 * @param lName The patient last name
	 * @param dob The patient date of birth
	 * @param gender The patient gender
	 * @param providerID The patient provider
	 */
	Patient(String fName, String mName, String lName, Date dob, String gender, int providerID) {
		
		this.fName = fName;
		this.mName = mName;
		this.lName = lName;
		this.bDay = dob;
		this.gender = gender;
		this.providerID = providerID;		
	}	
	
	/**
	 * 
	 * @return gender
	 */
	String getGender() {
		return this.gender;
	}
	
	/**
	 * Set the gender of a patient 
	 * @param gender The new gender
	 */
	void setGender(String gender) {
		this.gender = gender;
	}


	/**
	 * 
	 * @return Patient date of death
	 */
	Date getdDay() {
		//check to see if patient is actually dead
		return this.dDay;
	}
	
	/**
	 * Sets patient date of death
	 * @param dDay The updated patient date of death
	 */
	void setdDay(Date dDay) {
		this.dDay = dDay;
	}
	
	/**
	 * 
	 * @return Patient date of birth
	 */
	Date getbDay() {
		return this.bDay;
	}
	
	/**
	 * Sets patient date of birth
	 * @param bDay The patient date of birth
	 */
	void setbDay(Date bDay) {
		this.bDay = bDay;
	}
	
	/**
	 * 
	 * @return pt_id used to uniquely identify patient in the database
	 */
	int getID() {
		return this.pt_id;	
	}
	
	/**
	 * 
	 * @return Patient first name
	 */
	String getfName() {
		return this.fName;
	}
	
	/**
	 * Updates patient first name
	 * @param fName The updated first name
	 */
	void setfName(String fName) {
		this.fName = fName;
	}
	
	/**
	 * 
	 * @return The patient last name
	 */
	String getlName() {
		return this.lName;
	}
	
	/**
	 * Updates patient last name
	 * @param lName The updated patient last name
	 */
	void setlName(String lName) {
		this.lName = lName;
	}
	
	/**
	 * 
	 * @return The patient middle name
	 */
	String getmName() {
		return this.mName;
	}
	
	/**
	 * Updates patient middle name
	 * @param mName The updated patient middle name
	 */
	void setmName(String mName) {
		this.mName = mName;
	}
	
	/**
	 * 
	 * @return The provider id used to show the patient is a patient of a given physician
	 */
	int getProviderID() {		
		return this.providerID;
	}
	

	void setProvider(int providerID) {
		this.providerID = providerID;	
	}


	/**
	 * An ArrayList of Patients retrieved from the database
	 * @return ArrayList of patients
	 */
	ArrayList<Patient> getList() {
		return patientList;
	}
		
	
}
