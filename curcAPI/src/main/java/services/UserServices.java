package services;
import java.util.ArrayList;

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
@Path("user")
public class UserServices {

	@Path("users")
	@GET
	@Produces(MediaType.APPLICATION_JSON+"; charset=UTF-8")
	/**
	 * Send all users in the database to the app
	 * @return ArrayList of Users
	 */
	public Response getUsers() {
		
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
	
	@Path("username")
	@GET
	@Produces(MediaType.APPLICATION_JSON+"; charset=UTF-8")
	/**
	 * Return a User object retrieved from the database by username
	 * @param username
	 * @return The specified User object
	 */
	public Response getUserByUsernameName(@QueryParam("username") String username) {
		
		UserFacade iFacade = UserFacade.getInstance();
		
		User user = iFacade.getUserByUsername(username);
		
		if(user != null) {
			Gson gsonObj = new Gson();
			String result = gsonObj.toJson(user);
			
			ResponseBuilder rb = Response.ok(result, MediaType.TEXT_PLAIN);
			rb.status(200);
			return rb.build();		
		}
		else {
			return Response.status(700).build();
		}
		
	}
	
	@Path("authenticate")
	@GET
	@Produces(MediaType.APPLICATION_JSON +"; charset=UTF-8")
	public Response authenticateUser(@QueryParam("username") String username, @QueryParam("password") String password) {
		UserFacade iFacade = UserFacade.getInstance();
		
		User user = iFacade.authenticateUser(username, password);
		
		if(user != null) {
			Gson gsonObj = new Gson();
			String result = gsonObj.toJson(user);
			
			ResponseBuilder rb = Response.ok(result, MediaType.TEXT_PLAIN);
			rb.status(200);
			return rb.build();
		}
		else {
			return Response.status(401).build();
		}
	}
	
	@Path("create_user")
	@POST()
	@Produces(MediaType.APPLICATION_JSON+"; charset=UTF-8")
	@Consumes("application/x-www-form-urlencoded")
	/**
	 * Insert a new User into the database
	 * @param formFields The User object fields needed for the construction of the User object
	 * @return The newly created User object, successfully retrieved from the database after insertion
	 */
	public Response createUser(MultivaluedMap<String, String> formFields) {
		
		UserFacade iFacade = UserFacade.getInstance();
		String username = formFields.getFirst("username");
		String password = formFields.getFirst("password");
		String lName = formFields.getFirst("lName");
		String fName = formFields.getFirst("fName");
		String mName = formFields.getFirst("mName");
		String dob = formFields.getFirst("dob");
		String credentials = formFields.getFirst("credentials");
		String licenseNum = formFields.getFirst("licenseNum");
		String accessLevel = formFields.getFirst("accessLevel");
		
		User user = new User(username, password, lName, fName, mName, dob, credentials, licenseNum, accessLevel);
		
		User newUser = iFacade.createUser(user);
		
		if(newUser != null) {
			Gson theGsonObj = new Gson();
			String result = theGsonObj.toJson(newUser);
			
			ResponseBuilder rb = Response.ok(result, MediaType.TEXT_PLAIN);
			rb.status(201);
			return rb.build();
		}
		else {
			return Response.status(700).build();
		}
		
	}

}
