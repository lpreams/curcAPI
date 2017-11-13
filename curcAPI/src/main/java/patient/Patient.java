package patient;
import java.text.SimpleDateFormat;
import java.sql.Date;

/**
 * A class to represent a patient in the clinic
 * @author Alex
 *
 */
public class Patient {
	
	private int pt_id;
	private String lName;
	private String fName;
	private String mName;
	private java.sql.Date dob;
	private java.sql.Date dod = null;
	private int provID;
//	private int homePhone;
//	private int cellPhone;
	private String gender;
	
	/**
	 * Create a patient object containing information about a 
	 * patient that was returned from the database
	 * @param id The patient_id stored in the database as the primary key
	 * @param lName The patient last name
	 * @param fName The patient first name
	 * @param mName The patient middle name
	 * @param bDay The patient date of birth 
	 * @param dDay The patient date of death
	 * @param provID The patient provider
	 * @param gender The patient gender
	 */
	Patient(int id, String lName, String fName, String mName, java.sql.Date bDay, java.sql.Date dDay, int provID, String gender) {
		
		this.pt_id = id;
		this.fName = fName;
		this.mName = mName;
		this.lName = lName; 
		this.dob = bDay;
		this.dod = dDay;
		this.provID = provID;
		this.gender = gender.toUpperCase();
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
	public Patient(String lName, String fName, String mName, String bDay, int provID, String gender) {

		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
		this.lName = lName;		
		this.fName = fName;
		this.mName = mName;
		try {
			java.util.Date parsed = simpleDate.parse(bDay);
			this.dob = new java.sql.Date(parsed.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}	
		this.provID = provID;
		this.gender = gender.toUpperCase();		
	}	
	
	Patient(int pt_id, String lName, String fName, String mName, Date bDay, int provID, String gender) {

		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
		String birth = bDay.toString();
		
		this.lName = lName;		
		this.fName = fName;
		this.mName = mName;
		try {
			java.util.Date parsed = simpleDate.parse(birth);
			this.dob = new java.sql.Date(parsed.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}	
		this.provID = provID;
		this.gender = gender.toUpperCase();		
	}	

	int getID() { return this.pt_id; }
	
	String getfName() { return this.fName; }
	void setfName(String fName) { this.fName = fName; }
	
	String getlName() {	return this.lName; }
	void setlName(String lName) { this.lName = lName; }
	
	String getmName() { return this.mName; }
	void setmName(String mName) { this.mName = mName; }

	String getGender() { return this.gender; }
	void setGender(String gender) { this.gender = gender; }

	java.sql.Date getbDay() { return this.dob; }
	void setbDay(java.sql.Date bDay) { this.dob = bDay; }
	
	java.sql.Date getdDay() { return this.dod; }
	void setdDay(java.sql.Date dDay) { this.dod = dDay; }
			
	int getprovID() { return this.provID; }
    void setProvider(int provID) { this.provID = provID; }	
	
}
