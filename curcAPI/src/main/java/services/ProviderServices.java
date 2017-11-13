package services;
import java.sql.SQLException;
import java.text.ParseException;
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

import provider.Provider;
import provider.ProviderFacade;

@Path("provider")
public class ProviderServices {
	
	@Path("providers")
	@GET
	@Produces(MediaType.APPLICATION_JSON+"; charset=UTF-8")
	public Response getProviders() throws NamingException, SQLException, ClassNotFoundException {
		
		ProviderFacade iFacade = ProviderFacade.getInstance();
		
		ArrayList<Provider> resultArray = iFacade.getProviders();
		
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
	
	@Path("lName")
	@GET
	@Produces(MediaType.APPLICATION_JSON+"; charset=UTF-8")
	public Response getProviderByLName(@QueryParam("lName") String lName) throws NamingException, SQLException, ClassNotFoundException {
		ProviderFacade iFacade = ProviderFacade.getInstance();
		ArrayList<Provider> resultArray = iFacade.getProviderByLName(lName);
		
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
	
	@Path("id")
	@GET
	@Produces(MediaType.APPLICATION_JSON+"; charset=UTF-8")
	public Response getProviderByID(@QueryParam("id") int theID) throws NamingException, SQLException, ClassNotFoundException {

		ProviderFacade iFacade = ProviderFacade.getInstance();
		Provider provider = iFacade.getProviderByID(theID);
		
		if(provider != null) {
			Gson gsonObj = new Gson();
			String result = gsonObj.toJson(provider);
			
			ResponseBuilder rb = Response.ok(result, MediaType.TEXT_PLAIN);
			rb.status(200);
			return rb.build();
		}
		else {
			return Response.status(700).build();
		}
		
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON+"; charset=UTF-8")
	@Consumes("application/x-www-form-urlencoded")
	public Response createProvider(MultivaluedMap<String, String> formFields) {
		
		ProviderFacade iFacade = ProviderFacade.getInstance();
		String lName = formFields.getFirst("lName");
		String fName = formFields.getFirst("fName");
		String mName = formFields.getFirst("mName");
		String credentials = formFields.getFirst("credentials");
		String licenseNum = formFields.getFirst("licenseNum");
		String npi = formFields.getFirst("npi");
		String dob = formFields.getFirst("dob");
		
		Provider provider = new Provider(lName, fName, mName, credentials, licenseNum, npi, dob);
		
		Provider newProvider = iFacade.createProvider(provider);
		
		if(newProvider != null) {
			Gson theGsonObj = new Gson();
			String result = theGsonObj.toJson(newProvider);
			
			ResponseBuilder rb = Response.ok(result, MediaType.TEXT_PLAIN);
			rb.status(201);
			return rb.build();
		}
		else {
			return Response.status(700).build();
		}
	}
}
