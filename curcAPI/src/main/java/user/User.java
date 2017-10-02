package user;
import java.util.ArrayList;
import java.sql.Date;

/**
 * A class to represent a user who has access to the database
 * @author Alex
 *
 */
public class User {

	private int user_id;
	private String fName;
	private String lName;
	private String mName;
	private Date birth;
	private String credentials;
	private String licenseNum;
	private final String username;
	private String password; //has this
	private static ArrayList<User> userList;
	
	/**
	 * Creates a new user from the front end.
	 * @param fName The user first name
	 * @param lName The user last name
	 * @param username The user username
	 * @param password The user password
	 */
	public User(String fName, String lName, String username, String password) {
		 this.fName = fName;
		 this.lName = lName;
		 this.username = username;
		 this.password = password;
		 
	}
	
	/**
	 * Creates a new user that has been returned from the database. 
	 * @param user_id The user_id stored in the database as primary key.
	 * @param fName The user first name.
	 * @param lName The user last name
	 * @param username The user username
	 * @param password
	 */
	User(int user_id, String fName, String lName, String username, String password) {
		this.user_id = user_id;
		this.fName = fName;
		this.lName = lName;
		this.username = username;
		this.password = password;
	}
	
	/**
	 * 
	 * @return user_id
	 */
	int getUserID() {
		return this.user_id;
	}
	
	/**
	 * 
	 * @return fName
	 */
	String getfName() {
		return this.fName;
	}

	/**
	 * Set fName
	 * @param fName The new first name
	 */
	void setfName(String fName) {
		this.fName = fName;
	}
	
	/**
	 * 
	 * @return lName
	 */
	String getlName() {
		return this.lName;
	}
	
	/**
	 * Set lName
	 * @param lName
	 */
	void setlName(String lName) {
		this.lName = lName;
	}
	
	/**
	 * 
	 * @return username
	 */
	String getUsername() {
		return this.username;
	}
	
	/**
	 * 
	 * @return password
	 */
	String getPassword() {
		return this.password;
	}
	
	/**
	 * Set password
	 * @param password The new password
	 */
	void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * 
	 * @return User birthday
	 */
	Date getBDay() {
		return this.birth;
	}
	
	/**
	 * 
	 * @param bDay The user birthday
	 */
	void setBDay(Date bDay) {
		this.birth = bDay;
	}
	
	/**
	 * 
	 * @return credentials
	 */
	String getCredentials() {
		return this.credentials;
	}
	
	/**
	 * The credentials represent the certifications held 
	 * by a user (i.e. degrees, medical licenses, etc..)
	 * @param credentials
	 */
	void setCredentials(String credentials) {
		this.credentials = credentials;
	}
	
	/**
	 * 
	 * @return licenseNum
	 */
	String getLicense() {
		return this.licenseNum;
	}
	
	/**
	 * The professional license number of a 
	 * user, if they hold one
	 * @param license
	 */
	void setLicense(String license) {
		//make sure credentials are set as well 
		//if entering a license
		
		this.licenseNum = license;
	}
	
	
	
	
	
}
