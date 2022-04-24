package controller;

import model.Consumer;
import service.ProfileService;

import java.util.List;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 

@Path("/Profile")
public class ProfileController {
	
	@GET
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public String viewTask(@PathParam("username") String username) {
		
		Gson gson = new Gson();
		
		List<Consumer> consumerDetails = ProfileService.viewProfile(username);
		String jsonString  = gson.toJson(consumerDetails);
		
		return jsonString;
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertProfileDetails(@FormParam("name") String name, 
			@FormParam("address") String address, 
			@FormParam("mobile") String mobile, 
			@FormParam("nic") String nic, 
			@FormParam("email") String email, 
			@FormParam("username") String username, 
			@FormParam("password") String password) {
		
		//System.out.println("Task ID :"+taskID);
		//System.out.println("Worker ID :"+workerID);
		Boolean isTrue = ProfileService.insertProfileDetails(name, address, mobile, nic, email, username, password);
		
		String output;
		if(isTrue == true) {
			output = "Inserted Successfully..."	;
		} 
		else {
			output = "Error: Not Inserted Succussfully...";
		}
			
		return output;
	}
}
