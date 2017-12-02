package services;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.google.gson.Gson;

import study.Study;
import study.StudyFacade;

@Path("study")
public class StudyServices {
	
	@Path("studies")
	@GET
	@Produces(MediaType.APPLICATION_JSON+"; charset=UTF-8")
	public Response getStudyByName(@QueryParam("studyName") String name) {
		StudyFacade iFacade = StudyFacade.getInstance();
		ArrayList<Study> resultArray = iFacade.getStudyByName(name);
		
		if(resultArray != null) {
			Gson gsonObj = new Gson();
			String result = gsonObj.toJson(resultArray);	
			ResponseBuilder rb = Response.ok(result, MediaType.TEXT_PLAIN);
			rb.status(200);
			return rb.build(); }
		else return Response.status(700).build();
	}
	
	@Path("studies/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON+"; charset=UTF-8")
	public Response getStudyByID(@PathParam("id") int id) {
		StudyFacade iFacade = StudyFacade.getInstance();
		Study study = iFacade.getStudyByID(id);
		
		if(study != null) {
			Gson gsonObj = new Gson();
			String result = gsonObj.toJson(study);
			ResponseBuilder rb = Response.ok(result, MediaType.APPLICATION_JSON);
			rb.status(200);
			return rb.build(); }
		else return Response.status(700).build();
	}
	
	
}
