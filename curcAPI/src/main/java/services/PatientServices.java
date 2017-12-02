package services;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.google.gson.Gson;

import patient.Patient;
import patient.PatientFacade;

@Path("patient")
public class PatientServices {

	@Path("patients")
	@GET
	@Produces(MediaType.APPLICATION_JSON+"; charset=UTF-8")
	public Response getPatientByName(@QueryParam("lName") String lName, @QueryParam("fName") String fName) {
		PatientFacade iFacade = PatientFacade.getInstance();
		ArrayList<Patient> resultArray = iFacade.getPatientByName(fName, lName);
		
		if(resultArray != null) {
			Gson gsonObj = new Gson();
			String result = gsonObj.toJson(resultArray);
			ResponseBuilder rb = Response.ok(result, MediaType.TEXT_PLAIN);
			rb.status(200);
			return rb.build(); } 
		else return Response.status(700).build();	
	}

	
	@Path("patients/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON+"; charset=UTF-8")
	public Response getPatientByID(@PathParam("id") int id) {
		PatientFacade iFacade = PatientFacade.getInstance();
		Patient patient = iFacade.getPatientByID(id);
		
		if(patient != null) {
			Gson gsonObj = new Gson();
			String result = gsonObj.toJson(patient);	
			ResponseBuilder rb = Response.ok(result, MediaType.APPLICATION_JSON);
			rb.status(200);
			return rb.build(); } 
		else return Response.status(700).build(); 		
	}
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON+"; charset=UTF-8")
	@Consumes("application/x-www-form-urlencoded")
	public Response createPatient(MultivaluedMap<String, String> formFields) {
	
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
		
		PatientFacade iFacade = PatientFacade.getInstance();
		String lName = formFields.getFirst("lName");
		String fName = formFields.getFirst("fName");
		String mName = formFields.getFirst("mName");
		String birth = formFields.getFirst("bDay");
		String death = formFields.getFirst("dDay");
		String provider = formFields.getFirst("provID");
		String gender = formFields.getFirst("gender");
		int provID = Integer.parseInt(provider);
		java.sql.Date bDay = null;
		java.sql.Date dDay = null;
		try {
			java.util.Date parsed = simpleDate.parse(birth);
			bDay = new java.sql.Date(parsed.getTime()); 
			parsed = simpleDate.parse(death);
			dDay = new java.sql.Date(parsed.getTime());} 
		catch (Exception e) { e.printStackTrace(); }	
		
		Patient patient = new Patient(lName, fName, mName, bDay, dDay, provID, gender);
		Patient newPatient = iFacade.createPatient(patient);

		if(newPatient!= null) {
			Gson gsonObj = new Gson();
			String result = gsonObj.toJson(newPatient);
			ResponseBuilder rb = Response.ok(result, MediaType.TEXT_PLAIN);
			rb.status(201);
			return rb.build(); } 
		else return Response.status(700).build();
	}
	
	
	@POST
	@Path("patients/update_last")
	@Produces(MediaType.APPLICATION_JSON+"; charset=UTF-8")
	@Consumes("application/x-www-form-urlencoded")
	public Response updatePatientlName(MultivaluedMap<String, String> formFields) {
		PatientFacade iFacade = PatientFacade.getInstance();
		String id = formFields.getFirst("ptID");
		String newlName = formFields.getFirst("newlName");
		int ptID = Integer.parseInt(id);
		
		Patient updatedPatient = iFacade.updatePatientlName(ptID, newlName);
		
		if(updatedPatient != null) {
			Gson gsonObj = new Gson();
			String result = gsonObj.toJson(updatedPatient);
			ResponseBuilder rb = Response.ok(result, MediaType.TEXT_PLAIN);
			rb.status(201);
			return rb.build(); } 
		else return Response.status(700).build(); 
	}
	
	
	@POST
	@Path("patients/update_first")
	@Produces(MediaType.APPLICATION_JSON+"; charset=UTF-8")
	@Consumes("application/x-www-form-urlencoded")
	public Response updatePatientfName(MultivaluedMap<String, String> formFields) {
		PatientFacade iFacade = PatientFacade.getInstance();
		String id = formFields.getFirst("ptID");
		String newfName = formFields.getFirst("newfName");
		int ptID = Integer.parseInt(id);
		
		Patient updatedPatient = iFacade.updatePatientfName(ptID, newfName);
		
		if(updatedPatient != null) {
			Gson gsonObj = new Gson();
			String result = gsonObj.toJson(updatedPatient);
			ResponseBuilder rb = Response.ok(result, MediaType.TEXT_PLAIN);
			rb.status(201);
			return rb.build(); } 
		else return Response.status(700).build();	
	}

	
	@POST
	@Path("patients/update_middle")
	@Produces(MediaType.APPLICATION_JSON+"; charset=UTF-8")
	@Consumes("application/x-www-form-urlencoded")
	public Response updatePatientmName(MultivaluedMap<String, String> formFields) {
		PatientFacade iFacade = PatientFacade.getInstance();
		String id = formFields.getFirst("ptID");
		String newmName = formFields.getFirst("newmName");
		int ptID = Integer.parseInt(id);
		
		Patient updatedPatient = iFacade.updatePatientfName(ptID, newmName);
		
		if(updatedPatient != null) {
			Gson gsonObj = new Gson();
			String result = gsonObj.toJson(updatedPatient);
			ResponseBuilder rb = Response.ok(result, MediaType.TEXT_PLAIN);
			rb.status(201);
			return rb.build(); } 
		else return Response.status(700).build(); 
	}
	
	
}

