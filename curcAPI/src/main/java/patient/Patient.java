package patient;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
	private Date dob;
	private Date dod = null;
	private int provID;
//	private int homePhone;
//	private int cellPhone;
	private String gender;
	private static ArrayList<Patient> patientList;
	
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
	Patient(int id, String lName, String fName, String mName, Date bDay, Date dDay, int provID, String gender) {
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
		
		this.pt_id = id;
		this.fName = fName;
		this.mName = mName;
		this.lName = lName; 
		this.dob = bDay;
		this.dod = dDay;
		this.provID = provID;
		this.gender = gender;
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
			this.dob = (Date)simpleDate.parse(bDay);
		} catch (Exception e) {
			//add handling
		}	
		this.provID = provID;
		this.gender = gender;		
	}	
	
	Patient(int pt_id, String fName, String mName, String lName, String bDay, int provID, String gender) {

		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
		
		this.lName = lName;		
		this.fName = fName;
		this.mName = mName;
		try {
			this.dob = (Date)simpleDate.parse(bDay);
		} catch (Exception e) {
			//add handling
		}	
		this.provID = provID;
		this.gender = gender;		
		patientList.add(this);
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

	Date getbDay() { return this.dob; }
	void setbDay(Date bDay) { this.dob = bDay; }
	
	Date getdDay() { return this.dod; }
	void setdDay(Date dDay) { this.dod = dDay; }
			
	int getprovID() { return this.provID; }
    void setProvider(int provID) { this.provID = provID; }


	ArrayList<Patient> getList() { return patientList; }
			
	
}
