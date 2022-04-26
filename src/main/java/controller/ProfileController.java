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
		
		String output;
		String regexPattern = "\\d{3}-\\d{3}-\\d{4}";
		String regexEmailPattern = "^(.+)@(.+)$";
		
		if(name.isEmpty()){
			output = "Enter name.";
		}
		else if(!mobile.matches(regexPattern)) {
			output = "Enter valid mobile number.";
		}
		else if(!email.matches(regexEmailPattern)) {
			output = "Enter valid email address.";
		}
		else if(username.isEmpty()) {
			output = "Enter username.";
		}
		else if(password.isEmpty()) {
			output = "Enter password.";
		}
		else if(password.length() < 5) {
			output = "Password contain at least 5 charators.";
		}
	
		Boolean isTrue = ProfileService.insertProfileDetails(name, address, mobile, nic, email, username, password);
		
		
		if(isTrue == true) {
			output = "Inserted Successfully..."	;
		} 
		else {
			output = "Error: Not Inserted Succussfully...";
		}
			
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateProfileDetails(String profileDetails)
	{
	//Convert the input string to a JSON object
	 JsonObject consumerObj = new JsonParser().parse(profileDetails).getAsJsonObject();
	 
	//Read the values from the JSON object
	 String conId = consumerObj.get("consumerId").getAsString();
	 String name = consumerObj.get("name").getAsString();
	 String address = consumerObj.get("address").getAsString();
	 String mobile = consumerObj.get("mobile").getAsString();
	 String nic = consumerObj.get("nic").getAsString();
	 String email = consumerObj.get("email").getAsString();
	 String username = consumerObj.get("username").getAsString();
	 String password = consumerObj.get("password").getAsString();
	 
	 Boolean isTrue = ProfileService.updateProfileDetails(conId, name, address, mobile, nic, email, username, password);
	 
	 String output;
	 if(isTrue == true) {
		output = "Updated Successfully..."	;
	 } 
	 else {
		output = "Error: Not Updated Succussfully...";
	 }
	 
	return output;
	}
	
	@DELETE
	@Path("/{consumerId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteProfile(@PathParam("consumerId") String consumerId) {
		
		Boolean isTrue = ProfileService.deleteProfile(consumerId);

		String output;
		 if(isTrue == true) {
			output = "Deleted Successfully..."	;
		 } 
		 else {
			output = "Error: Not Deleted Succussfully...";
		 }
		 
		return output;
	}
	
}
