package services;

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

import sponsor.Sponsor;
import sponsor.SponsorFacade;

@Path("sponsor")
public class SponsorServices {

	@Path("sponsors")
	@GET
	@Produces(MediaType.APPLICATION_JSON+"; charset=UTF-8")
	public Response getSponsorByName(@QueryParam("name") String name) {
		SponsorFacade iFacade = SponsorFacade.getInstance();
		ArrayList<Sponsor> resultArray = iFacade.getSponsorsByName(name);
		
		if(resultArray != null) {
			Gson gsonObj = new Gson();
			String result = gsonObj.toJson(resultArray);
			
			ResponseBuilder rb = Response.ok(result, MediaType.TEXT_PLAIN);
			rb.status(200);
			return rb.build(); }
		else return Response.status(700).build(); 
	}
	
	
	@Path("sponosrs/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON+"; charset=UTF-8")
	public Response getSponsorByID(@PathParam("id") int id) {
		SponsorFacade iFacade = SponsorFacade.getInstance();
		Sponsor sponsor = iFacade.getSponsorByID(id);
		
		if(sponsor!=null) {
			Gson gsonObj = new Gson();
			String result = gsonObj.toJson(sponsor);
			
			ResponseBuilder rb = Response.ok(result, MediaType.TEXT_PLAIN);
			rb.status(200);
			return rb.build();  }
		else return Response.status(700).build(); 
	}
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON+"; charset=UTF-8")
	@Consumes("application/x-www-form-urlencoded")
	public Response createSponsor(MultivaluedMap<String, String> formFields) {
		SponsorFacade iFacade = SponsorFacade.getInstance();
		String sponsorName = formFields.getFirst("sponsorName");
		
		Sponsor sponsor = new Sponsor(sponsorName);
		
		Sponsor newSponsor = iFacade.createSponsor(sponsor);
		
		if(newSponsor!=null) {
			Gson gsonObj = new Gson();
			String result = gsonObj.toJson(newSponsor);
			
			ResponseBuilder rb = Response.ok(result, MediaType.TEXT_PLAIN);
			rb.status(201);
			return rb.build(); }
		else return Response.status(700).build(); 
	}
	
		
	
}
