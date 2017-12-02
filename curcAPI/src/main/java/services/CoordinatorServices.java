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

import coordinator.Coordinator;
import coordinator.CoordinatorFacade;

@Path("coordinator")
public class CoordinatorServices {
	
	@Path("coordinators")
	@GET
	@Produces(MediaType.APPLICATION_JSON+"; charset=UTF-8")
	public Response getCoordinators() {
		CoordinatorFacade iFacade = CoordinatorFacade.getInstance();
		ArrayList<Coordinator> resultArray = iFacade.getCoordinators();
		
		if(resultArray != null) {
			Gson gsonObj = new Gson();
			String result = gsonObj.toJson(resultArray);
			ResponseBuilder rb = Response.ok(result, MediaType.TEXT_PLAIN);
			rb.status(200);
			return rb.build();
		} else return Response.status(700).build(); 
	}
	
	@Path("id")
	@GET
	@Produces(MediaType.APPLICATION_JSON+"; charset=UTF-8")
	public Response getCoordinatorByName(@QueryParam("id") int theID) {
		CoordinatorFacade iFacade = CoordinatorFacade.getInstance();
		Coordinator coord = iFacade.getCoordinatorByID(theID);
		
		if(coord != null) {
			Gson gsonObj = new Gson();
			String result = gsonObj.toJson(coord);
			ResponseBuilder rb = Response.ok(result, MediaType.TEXT_PLAIN);
			rb.status(200);
			return rb.build();
		} else return Response.status(700).build(); 	
	}
	
	@POST 
	@Produces(MediaType.APPLICATION_JSON+" charset=UTF-8")
	@Consumes("application/x-www-form-urlencoded")
	public Response createCoordinator(MultivaluedMap<String, String> formFields) {
		CoordinatorFacade iFacade = CoordinatorFacade.getInstance();
		String type = formFields.getFirst("type");
		String name = formFields.getFirst("name");
		
		Coordinator coord = new Coordinator(type, name);
		Coordinator newCoord = iFacade.createCoordinator(coord);
		
		if(newCoord != null) {
			Gson theGsonObj = new Gson();
			String result = theGsonObj.toJson(newCoord);
			ResponseBuilder rb = Response.ok(result, MediaType.TEXT_PLAIN);
			rb.status(201);
			return rb.build();
		} else return Response.status(700).build();
	}
	

}
