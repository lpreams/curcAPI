package provider;
import java.util.ArrayList;

/**
 * A class to represent a provider within the 
 * clinic (anyone who sees patients)
 * @author Alex
 *
 */
public class Provider {
	
	private static ArrayList<Provider> providerList;
	private int providerID;
	private String fName;
	private String lName;
	private String credentials;
	private String licenseNum;
	private String npi;
	
	/**
	 * Creates a Provider object with data that has been returned from the database
	 * @param providerID prov_id used to uniquely identify a provider in the database
	 * @param fName The provider first name
	 * @param lName The provider last name
	 * @param credentials The provider credentials (MD, NP, etc...)
	 * @param licenseNum The provider license number (if they have one)
	 * @param npi The provider NPI number (if they have one)
	 */
	private Provider(int providerID, String fName, String lName, String credentials, String licenseNum, String npi) {
		this.providerID = providerID;
		this.fName = fName;
		this.lName = lName;
		this.credentials = credentials;
		this.licenseNum = licenseNum;
		this.npi = npi;
		
	}
	
	/**
	 * Creates a provider object to be inserted into the database
	 * @param fName The provider first name
	 * @param lName The provider last name
	 * @param credentials The provider credentials
	 */
	private Provider(String fName, String lName, String credentials) {
		this.providerID = providerID;
		this.fName = fName;
		this.lName = lName;
		this.credentials = credentials;
	}
	
	String getFName() {
		return this.fName;
	}
	
	/**
	 * 
	 * @return lName
	 */
	String getLName() {
		return this.lName;
	}
	
	/**
	 * Updates the provider last name
	 * @param lName
	 */
	void setLName(String lName) {
		this.lName = lName;
	}
	
	
	
	
	
	
	
	
	
}
