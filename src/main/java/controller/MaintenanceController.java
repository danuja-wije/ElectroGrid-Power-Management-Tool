package controller;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import model.Interruption;
import service.MaintenanceService;
import service.MaintenencrServiceImpl;

@Path("/")
public class MaintenanceController {
	MaintenanceService maintenanceService = new MaintenencrServiceImpl();
	Gson gson = new Gson();

	@GET	
	@Path("/Interruptions")
	@Produces(MediaType.APPLICATION_JSON)
	public String allInterruptions() {
		List<Interruption> list = maintenanceService.allInterruptions();
		String jsonString = gson.toJson(list);
		return jsonString;
	}

	@POST
	@Path("/Interruptions")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)

	public String insertInterruption(@FormParam("intType") String intType,@FormParam("title") String title,@FormParam("description") String description,@FormParam("startDate") String startDate,@FormParam("endDate") String endDate,@FormParam("approval")String approval) {
		Interruption interruption = new Interruption(intType, title, description, startDate, endDate, null, approval);
		String output = maintenanceService.insertInterruption(interruption);
		return output;
	}

	@PUT
	@Path("/Interruptions")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)

	public String updateInterruption(@FormParam("id") int id,@FormParam("intType") String intType,@FormParam("title") String title,@FormParam("description") String description,@FormParam("startDate") String startDate,@FormParam("endDate") String endDate,@FormParam("approval")String approval) {
		Interruption interruption = new Interruption(intType, title, description, startDate, endDate, null, approval);
		interruption.setId(id);
		String output = maintenanceService.updateInterruption(interruption);
		return output;
	}

	@DELETE
	@Path("/Interruptions/{id}")
	@Produces(MediaType.TEXT_PLAIN)

	public String deleteInterruption(@PathParam("id") int id) {
		String output = maintenanceService.deleteInterruption(id);
		return output;
	}


}
