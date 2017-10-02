package user;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import database.DatabaseAccess;

import java.util.ArrayList;
import java.util.Arrays;

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
	private UserFacade() throws NamingException, SQLException {
		this.uao = DatabaseAccess.getInstance();
	}
	
	/**
	 * Returns the instance of the singleton facade
	 * @return singleton
	 * @throws NamingException
	 * @throws SQLException
	 */
	public static UserFacade getInstance() throws NamingException, SQLException {
		if(singleton == null) {
			singleton = new UserFacade();
		}
		return singleton;
	}
	
	/**
	 * Retrieves all users from the database
	 * @return ArrayList of Users
	 * @throws SQLException
	 */
	public ArrayList<User> getUsers() throws SQLException {
		Connection con = uao.getConnection();
		PreparedStatement stmt = con.prepareStatement("SELECT id, fName, lName, username, password FROM user"); 
		ResultSet rs = stmt.executeQuery();
		
		ArrayList<User> userArray = new ArrayList<User>();
		
		int count = 0;
		while(rs.next()) {
			int user_id = rs.getInt("id");
			String fName = rs.getString("fName");
			String lName = rs.getString("lName");
			String username = rs.getString("username");
			String password = rs.getString("password");
			User user = new User(user_id, fName, lName, username, password);
			userArray.add(user);
			count++;		
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
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	ArrayList<User> getUserByName(String firstName, String lastName) throws SQLException, ClassNotFoundException {
		
		Connection con = uao.getConnection();
		
		PreparedStatement stmt = con.prepareStatement("SELECT id, fName, lName, username, password WHERE fName=? AND lName=?"); 
				stmt.setString(1, firstName); 
				stmt.setString(2, lastName);
				ResultSet rs = stmt.executeQuery();
				
				ArrayList<User> userArray = new ArrayList<User>(); 
				int count = 0;
				while(rs.next()) {
					int user_id = rs.getInt("id");
					String fName = rs.getString("fName");
					String lName = rs.getString("lName");
					String username = rs.getString("username");
					String password = rs.getString("password");
					User user = new User(user_id, fName, lName, username, password);
					userArray.add(user);
					count++;		
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
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<User> getUserByUsername(String uname) throws SQLException, ClassNotFoundException {
		
		Connection con = uao.getConnection();
		
		PreparedStatement stmt = con.prepareStatement("SELECT id, fName, lName, username, password WHERE username=?"); 
				stmt.setString(1, uname); 
				ResultSet rs = stmt.executeQuery();
				
				ArrayList<User> userArray = new ArrayList<User>(); //fix the size of that array...
				int count = 0;
				while(rs.next()) {
					int user_id = rs.getInt("id");
					String fName = rs.getString("fName");
					String lName = rs.getString("lName");
					String username = rs.getString("username");
					String password = rs.getString("password");
					User user = new User(fName, lName, username, password);
					userArray.add(user);
					count++;		
				}
				
				if(count>0) {
					return userArray;
				}
				else {
					return null;
				}
	}
	
	/**
	 * Inserts a new User into the datbase
	 * @param user The user to be inserted
	 * @return The newly-created user that has been successfully retrieved from the database after insertion
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<User> createUser(User user) throws SQLException, ClassNotFoundException{
		
		Connection con = uao.getConnection();
		
		int user_id = 0;
		String fName = user.getfName();
		String lName = user.getlName();
		String username = user.getUsername();
		String password = user.getPassword();
		
		PreparedStatement createStmt = con.prepareStatement("INSERT INTO user (fName, lName, username, password) VALUES (?,?,?,?");
		createStmt.setString(1, fName);
		createStmt.setString(2, lName);
		createStmt.setString(3, username);
		createStmt.setString(4, password);
	
		int res = createStmt.executeUpdate();
		
		if(res==1) {
			PreparedStatement retrieveStmt = con.prepareStatement("Select * from user where user_id=? AND fName=? AND lName=? AND username=? AND password=?");
			retrieveStmt.setInt(1, user_id);
			retrieveStmt.setString(2, fName);
			retrieveStmt.setString(3, lName);
			retrieveStmt.setString(4, username);
			retrieveStmt.setString(5, password);
			ResultSet rs = retrieveStmt.executeQuery();
			
			ArrayList<User> userArray = new ArrayList<User>();
			
			int count = 0;
			while(rs.next()) {
				int new_id = rs.getInt("id");
				String new_fName = rs.getString("fName");
				String new_lName = rs.getString("lName");
				String new_username = rs.getString("username");
				String new_password = rs.getString("password");				
				User newUser = new User(new_id, new_fName, new_lName, new_username, new_password);
				userArray.add(user);			
			}
			
			if(count>0) {
				return userArray;
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
