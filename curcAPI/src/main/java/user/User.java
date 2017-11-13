package user;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A class to represent a user who has access to the database
 * @author Alex
 *
 */
public class User {

	private int user_id;
	private final String username;
	private String password;
	private String lName;
	private String fName;
	private String mName;
	private Date dob;
	private String credentials;
	private String licenseNum;
	private String accessLevel;
	
	private static ArrayList<User> userList;
	
	/**
	 * Creates a new user from the front end.
	 * @param fName The user first name
	 * @param lName The user last name
	 * @param username The user username
	 * @param password The user password
	 */
	public User(String username, String password, String lName, String fName, String mName, 
			String bDay, String credentials, String licenseNum, String accessLevel) {
		 
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
		
		this.username = username;
		this.password = password;
		this.lName = lName;
		this.fName = fName;
		this.mName = mName; 
		try {
			this.dob = (Date)simpleDate.parse(bDay);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.credentials = credentials;
		this.licenseNum = licenseNum;
		this.accessLevel = accessLevel;
		userList.add(this);
	}
	
	/**
	 * Creates a new user that has been returned from the database. 
	 * @param user_id The user_id stored in the database as primary key.
	 * @param fName The user first name.
	 * @param lName The user last name
	 * @param username The user username
	 * @param password
	 */
	User(int user_id, String username, String lName, String fName, String accessLevel) {
		this.user_id = user_id;
		this.username = username;
		this.fName = lName;
		this.lName = fName;
		this.accessLevel = accessLevel;

	}
	

	int getUserID() { return this.user_id; }
	
	String getfName() {	return this.fName; }
	void setfName(String fName) { this.fName = fName; }
	
	String getlName() {	return this.lName; }
	void setlName(String lName) { this.lName = lName; }
	
	String getmName() { return this.mName; }
	void setmName(String mName) { this.mName = mName; }
	
	String getUsername() { return this.username; }

	String getPassword() { return this.password; }
	void setPassword(String password) {	this.password = password; }

	Date getBDay() { return this.dob; }
	void setBDay(Date bDay) { this.dob = bDay; }

	String getCredentials() { return this.credentials; }
	void setCredentials(String credentials) { this.credentials = credentials; }
	
	String getLicense() { return this.licenseNum; }
	void setLicense(String license) { this.licenseNum = license; }
	
	String getAccesslevel() { return this.getAccesslevel(); }
	void setAccessLevel(String accessLevel) { this.accessLevel = accessLevel; }

}