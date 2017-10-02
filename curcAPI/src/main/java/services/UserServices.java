package services;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.google.gson.Gson;

import user.User;
import user.UserFacade;

/**
 * Allows interaction with the ClinicApp and indirectly 
 * retrieves information from the database
 * @author Alex
 *
 */
@Path("/user")
public class UserServices {

	
	@GET
	@Produces("text/plain")
	/**
	 * Send all users in the database to the app
	 * @return ArrayList of Users
	 * @throws NamingException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Response getUsers() throws NamingException, SQLException, ClassNotFoundException {
		
		UserFacade iFacade = UserFacade.getInstance();
		
		ArrayList<User> resultArray = iFacade.getUsers();
		
		if(resultArray != null) {
			Gson gsonObj = new Gson();
			String result = gsonObj.toJson(resultArray);
			
			ResponseBuilder rb = Response.ok(result, MediaType.TEXT_PLAIN);
			rb.status(200);
			return rb.build();
		}
		else {
			return Response.status(700).build();
		}
	}
	
	@Path("user")
	@GET
	@Produces("text/plain")
	/**
	 * Return a User object retrieved from the database by username
	 * @param username
	 * @return The specified User object
	 * @throws NamingException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Response getUserByUsernameName(@QueryParam("username") String username) throws NamingException, SQLException, ClassNotFoundException {
		
		UserFacade iFacade = UserFacade.getInstance();
		
		ArrayList<User> resultArray = iFacade.getUserByUsername(username);
		
		if(resultArray != null) {
			Gson theGsonObj = new Gson();
			String result = theGsonObj.toJson(resultArray);
			
			ResponseBuilder rb = Response.ok(result, MediaType.TEXT_PLAIN);
			rb.status(200);
			return rb.build();		
		}
		else {
			return Response.status(700).build();
		}
		
	}
	
	@Path("create")
	@POST
	@Produces("text/plain")
	@Consumes("application/x-www-form-urlencoded")
	/**
	 * Insert a new User into the database
	 * @param formFields The User object fields needed for the construction of the User object
	 * @return The newly created User object, successfully retrieved from the database after insertion
	 * @throws NamingException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Response createUser(MultivaluedMap<String, String> formFields) throws NamingException, SQLException, ClassNotFoundException {
		
		UserFacade iFacade = UserFacade.getInstance();
		String fName = formFields.getFirst("fName");
		String lName = formFields.getFirst("lName");
		String username = formFields.getFirst("username");
		String password = formFields.getFirst("password");
		
		User user = new User(fName, lName, username, password);
		
		ArrayList<User> resultArray = iFacade.createUser(user);
		
		if(resultArray != null) {
			Gson theGsonObj = new Gson();
			String result = theGsonObj.toJson(resultArray);
			
			ResponseBuilder rb = Response.ok(result, MediaType.TEXT_PLAIN);
			rb.status(201);
			return rb.build();
		}
		else {
			return Response.status(700).build();
		}
		
	}

	
	
	
	
	
	
	
	



}
