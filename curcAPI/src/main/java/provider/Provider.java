package provider;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;

/**
 * A class to represent a provider within the 
 * clinic (anyone who sees patients)
 * @author Alex
 *
 */
public class Provider {
	
	private static ArrayList<Provider> providerList;
	private int providerID;
	private String lName;
	private String fName;
	private String mName;
	private String credentials;
	private String licenseNum;
	private String npi;
	private Date dob;
	
	private transient SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * Creates a Provider object with data that has been returned from the database
	 * @param providerID prov_id used to uniquely identify a provider in the database
	 * @param fName The provider first name
	 * @param lName The provider last name
	 * @param credentials The provider credentials (MD, NP, etc...)
	 * @param licenseNum The provider license number (if they have one)
	 * @param npi The provider NPI number (if they have one)
	 */
	public Provider(String lName, String fName, String mName, 
			String bDay, String credentials, String licenseNum, String npi) {
		
		
		
		this.lName = lName;
		this.fName = fName;
		this.mName = mName;
		try {
			java.util.Date parsed = simpleDate.parse(bDay);
			this.dob = new java.sql.Date(parsed.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}	
		this.credentials = credentials;
		this.licenseNum = licenseNum;
		this.npi = npi;
		providerList.add(this);
	}
	
	/**
	 * Creates a provider object to be inserted into the database
	 * @param fName The provider first name
	 * @param lName The provider last name
	 * @param credentials The provider credentials
	 */
	Provider(int providerID, String lName, String fName, String credentials) {
		this.providerID = providerID;
		this.lName = lName;
		this.fName = fName;
		this.credentials = credentials;

	}
	
	Provider(int providerID, String lName, String fName, String credentials, String licenseNum, String npi) {
		this.providerID = providerID;
		this.lName = lName;
		this.fName = fName;
		this.credentials = credentials;
		this.licenseNum = licenseNum;
		this.npi = npi;
		
	}
	
	public int getID() { return this.providerID; }
	
	String getLName() {	return this.lName; }
	void setLName(String lName) { this.lName = lName; }
	
	String getFName() {	return this.fName; }
	void setFName(String fName) { this.fName = fName; }
	
	String getMName() { return this.mName; }
	void setMName (String mName) { this.mName = mName; }
		
	String getLicense() { return this.licenseNum; }
	void setLicense(String license) { this.licenseNum = license; }
	
	String getCredentials() { return this.credentials; }
	void setCredentials(String credentials) { this.credentials = credentials; }
	
	String getNPI() { return this.npi; }
	void setNPI(String npi) { this.npi = npi; }
	
	Date getDOB() { return this.dob; }
	void setDOB(java.sql.Date dob) { this.dob = dob; }
	
	ArrayList<Provider> getList() {	return providerList; }
	
		
}
