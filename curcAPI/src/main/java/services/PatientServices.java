package services;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.google.gson.Gson;

import patient.Patient;
import patient.PatientFacade;

@Path("")
public class PatientServices {

	@Path("/patients")
	@GET
	@Produces
	public Response getPatients() throws NamingException, SQLException, ClassNotFoundException {
		
		PatientFacade iFacade = PatientFacade.getInstance();
		
		ArrayList<Patient> resultArray = iFacade.getPatients();
		
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

	public Response getPatientByName(@QueryParam("fName") String fName, @QueryParam("lName") String lName) throws NamingException, SQLException, ClassNotFoundException {
		PatientFacade iFacade = PatientFacade.getInstance();
		ArrayList<Patient> resultArray = iFacade.getPatientByName(fName, lName);
		
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

}
