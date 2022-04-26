package controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import client.ClientManager;
import model.Interruption;
import service.MaintenanceService;
import service.MaintenencrServiceImpl;

@Path("/")
public class MaintenanceController {
	MaintenanceService maintenanceService = new MaintenencrServiceImpl();
	ClientManager clientManager = new ClientManager();
	Gson gson = new Gson();

	@GET	
	@Path("/Interruptions")
	@Produces(MediaType.APPLICATION_JSON)
	public String allInterruptions(@HeaderParam("Authorization") String auth) {
		String currentUser = clientManager.getCurrentUser(auth);
		if(currentUser.equals("login failed")) {
			String output = "login failed";
			String jsonString = gson.toJson(output);
			return jsonString;
		}else {
			List<Interruption> list = maintenanceService.allInterruptions();
			String jsonString = gson.toJson(list);
			return jsonString;
		}

	}

	@POST
	@Path("/Interruptions")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)

	public String insertInterruption(@FormParam("intType") String intType,@FormParam("title") String title,@FormParam("description") String description,@FormParam("startDate") String startDate,@FormParam("endDate") String endDate,@FormParam("approval")String approval,@FormParam("custIDs") String custIDs,@HeaderParam("Authorization") String auth) {

		String[] list_str = custIDs.split(";");
		List<String>list = new ArrayList<String>();
		for (String string : list_str) {
			list.add(string);
		}
		String output = "";
		String currentUser = clientManager.getCurrentUser(auth);
		System.out.println(currentUser);
		if(currentUser.equals("login failed")) {
			output = "login failed";
		}
		else {
			Interruption interruption = new Interruption(intType, title, description, startDate, endDate, list, approval,currentUser);
			output = maintenanceService.insertInterruption(interruption);
		}

		return output;
	}

	@PUT
	@Path("/Interruptions")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)

	public String updateInterruption(@FormParam("id") int id,@FormParam("intType") String intType,@FormParam("title") String title,@FormParam("description") String description,@FormParam("startDate") String startDate,@FormParam("endDate") String endDate,@FormParam("approval")String approval,@HeaderParam("Authorization") String auth) {
		String output = "";
		String currentUser = clientManager.getCurrentUser(auth);
		System.out.println(currentUser);
		if(currentUser.equals("login failed")) {
			output = "login failed";
		}else {
			Interruption interruption = new Interruption(intType, title, description, startDate, endDate, null, approval,currentUser);
			interruption.setId(id);
			output = maintenanceService.updateInterruption(interruption);	
		}


		return output;
	}

	@PUT
	@Path("/Interruptions/effected")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)

	public String updateEffectedCustomer(@FormParam("id") int id,@FormParam("custIDs") String custIDs,@HeaderParam("Authorization") String auth) {

		String output = "";
		String currentUser = clientManager.getCurrentUser(auth);
		System.out.println(currentUser);
		if(currentUser.equals("login failed")) {
			output = "login failed";
		}else {
			String[] list = custIDs.split(";");
			output = maintenanceService.updateEffectedCustomer(id,list);
		}

		return output;
	}


	@DELETE
	@Path("/Interruptions/{id}")
	@Produces(MediaType.TEXT_PLAIN)

	public String deleteInterruption(@PathParam("id") int id,@HeaderParam("Authorization") String auth) {

		String output = "";
		String currentUser = clientManager.getCurrentUser(auth);
		System.out.println(currentUser);
		if(currentUser.equals("login failed")) {
			output = "login failed";
		}else {
			output = maintenanceService.deleteInterruption(id);
		}

		return output;
	}


}
