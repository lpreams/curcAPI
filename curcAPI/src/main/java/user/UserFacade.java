package user;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import database.DatabaseAccess;

/**
 * A singleton facade class that uses a DatabaseAccess 
 * object to connect to the clinic database
 * @author Alex
 *
 */
public class UserFacade {
	
	private static UserFacade singleton;
	private DatabaseAccess uao;
	
	/**
	 * Creates an instance of the singleton facade
	 * @throws NamingException
	 * @throws SQLException
	 */
	public UserFacade() {
		try{
			this.uao = DatabaseAccess.getInstance();
		} catch(NamingException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Returns the instance of the singleton facade
	 * @return singleton
	 */
	public static UserFacade getInstance() {
		if(singleton == null) {
			singleton = new UserFacade();
		}
		return singleton;
	}
	
	
	/**
	 * Retrieves all users from the database
	 * @return ArrayList of Users
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	public ArrayList<User> getUsers() {

		int count = 0;
		ArrayList<User> userArray = new ArrayList<User>();
		
		try{
			Class.forName("org.h2.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try{
			Connection con = uao.getConnection();

			PreparedStatement stmt = con.prepareStatement
					("SELECT userID, username, userLName, userFName, userAccessLevel FROM user"); 
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				int user_id = rs.getInt("userID");
				String username = rs.getString("username");
				String lName = rs.getString("userLName");
				String fName = rs.getString("userFName");
				String accessLevel = rs.getString("userAccessLevel");
				User user = new User(user_id, username, lName, fName, accessLevel);
				userArray.add(user);
				count++;		
			}
			con.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		if(count>0) {
			return userArray;
		}
		else{
			return null;
		}
	}

	
	/**
	 * Search the database for a user by first and last name
	 * @param firstName
	 * @param lastName
	 * @return ArrayList of Users with matching names
	 */
	ArrayList<User> getUserByName(String firstName, String lastName) {
		
		try{
			Class.forName("org.h2.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<User> userArray = new ArrayList<User>(); 
		int count = 0;
		
		try{
			Connection con = uao.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT userID, username, userLName, userFName, userAccessLevel WHERE fName=? AND lName=?"); 
				stmt.setString(1, firstName); 
				stmt.setString(2, lastName);
				ResultSet rs = stmt.executeQuery();
				

				while(rs.next()) {
					int user_id = rs.getInt("userID");
					String username = rs.getString("username");
					String lName = rs.getString("userLName");
					String fName = rs.getString("userFName");
					String accessLevel = rs.getString("userAccessLevel");
					User user = new User(user_id, username, fName, lName, accessLevel);
					userArray.add(user);
					count++;		
				}
				con.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		if(count>0) {
			return userArray;
		}
		else {
			return null;
		}
	}
	
	
	/**
	 * Searches the database for a user by their unique username
	 * @param uname The username
	 * @return ArrayList containing the User returned by the database
	 */
	public User getUserByUsername(String uname) {
		
		try{
			Class.forName("org.h2.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		User user = null;
		int count = 0;
		
		try{
			Connection con = uao.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT userID, username, userLName, userFName, userAccessLevel FROM user WHERE username=?"); 
			stmt.setString(1, uname); 
			ResultSet rs = stmt.executeQuery();
				
			while(rs.next()) {
				String username = rs.getString("username");
				int user_id = rs.getInt("userID");
				String lName = rs.getString("userLName");
				String fName = rs.getString("userFName");
				String accessLevel = rs.getString("userAccessLevel");
				user = new User(user_id, username, lName, fName, accessLevel);
				count++;		
			}
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		if(count>0) {
			return user;
		}
		else {
			return null;
		}
	}
	
	
	public User authenticateUser(String uname, String password) {

		User user = null;
		String pword = null;
		
		try{
			Class.forName("org.h2.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try{
			Connection con = uao.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT userID, username, password, userLName, userFName, userAccessLevel FROM user where username=?");
			stmt.setString(1, uname);
			ResultSet rs = stmt.executeQuery();
		
			int userID;
			String username = null;

			String lName = null;
			String fName = null;
			String accessLevel = null;
				
			while(rs.next()) {
				userID = rs.getInt("userID");
				username = rs.getString("username");
				pword = rs.getString("password");
				lName = rs.getString("userLName");
				fName = rs.getString("userFName");
				accessLevel = rs.getString("userAccessLevel");
				user = new User(userID, username, lName, fName, accessLevel);
			}
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		if(password != null) {
			if(password.equals(pword)) {
				return user;
			}
			else return null;
		}
		else {
			return null;
		}
	}
	
	
	/**
	 * Inserts a new User into the database
	 * @param user The user to be inserted
	 * @return The newly-created user that has been successfully retrieved from the database after insertion
	 */
	public User createUser(User user) {
		
		try{
			Class.forName("org.h2.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
			
		String username = user.getUsername();
		String password = user.getPassword();
		String lName = user.getlName();
		String fName = user.getfName();
		String mName = user.getmName();
		Date dob = user.getBDay();
		String credentials = user.getCredentials();
		String licenseNum = user.getLicense();
		String accessLevel = user.getAccesslevel();
		
		int res = 0;
		Connection con = null;
		
		try{
			con = uao.getConnection();
			PreparedStatement createStmt = con.prepareStatement
					("INSERT INTO user (userID, username, password, userLName, userFName, userMName, userCredentials, userLicenseNum, userAccessLevel) VALUES (?,?,?,?,?,?,?,?,?)");
			createStmt.setString(1, null);
			createStmt.setString(2, username);
			createStmt.setString(3, password);
			createStmt.setString(4, lName);
			createStmt.setString(5, fName);
			createStmt.setString(6, mName);
			createStmt.setDate(7, (java.sql.Date) dob);
			createStmt.setString(8, credentials);
			createStmt.setString(9, licenseNum);
			createStmt.setString(10, accessLevel);
	
			res = createStmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}

		if(res==1) {
			int count = 0;
			User newUser = null;
			try{
				PreparedStatement retrieveStmt = con.prepareStatement
						("Select userID, username, userLName, userFName, userAccessLevel from user where username=? AND userLName=? AND userFName=? AND userAccessLevel=?");
				retrieveStmt.setString(1, username);
				retrieveStmt.setString(2, lName);
				retrieveStmt.setString(3, fName);
				retrieveStmt.setString(4, accessLevel);
				ResultSet rs = retrieveStmt.executeQuery();

				while(rs.next()) {
					int new_id = rs.getInt("userID");
					String new_username = rs.getString("username");		
					String new_lName = rs.getString("userLName");
					String new_fName = rs.getString("userFName");
					String new_accessLevel = rs.getString("userAccessLevel");
		
					newUser = new User(new_id, new_username, new_lName, new_fName, new_accessLevel);		
					count++;
				}
				con.commit();
				con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
	
			if(count>0) {
				return newUser;
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
