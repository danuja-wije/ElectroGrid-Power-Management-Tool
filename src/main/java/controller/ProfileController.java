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
}
