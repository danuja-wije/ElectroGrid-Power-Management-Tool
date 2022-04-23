package controller;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.google.gson.Gson;

import model.Inventory;
import service.InventoryService;
import service.InventoryServiceImpl;

@Path("/Inventory")
public class InventoryController {
	
	InventoryService invService = new InventoryServiceImpl();
	
	ArrayList<Inventory> invObjs = new ArrayList<>();
	
	//Insert 
	@POST
	@Path("/Insert")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String createInventory( @FormParam("invID") int id,@FormParam("invItemCode") String code,@FormParam("invItemName") String name,
								@FormParam("stockQty") int qty, @FormParam("manufactYr") String manufact, @FormParam("latestRepairDate") String repair,
								  @FormParam("createdTime") String created, @FormParam("updatedTime") String updated){
		
			String output = invService.insertInventory(new Inventory(id,code,name,qty,manufact,repair,created,updated));
			return output;
	}
	
	
	//View a specific record
	@GET
	@Path("/View/{invID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String viewSpecificInventory(@PathParam("invID") int invID) {
		Gson gson = new Gson();
		invObjs = invService.readInventory(invID);
		String jsonString  = gson.toJson(invObjs);
		return jsonString;
		//return invService.readInventory(invID);
	}
	
	//View all records
	@GET
	@Path("/View")
	@Produces(MediaType.APPLICATION_JSON)
	public String viewAllInventory() {
		Gson gson = new Gson();
		invObjs = invService.readInventory();
		String jsonString  = gson.toJson(invObjs);
		return jsonString;
		//return invService.readInventory();
	}
	
	//Update
	@PUT
	@Path("/Update/{invID}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String editInventory(@PathParam("invID") int invID , 
							@FormParam("invItemCode") String code,
							@FormParam("invItemName") String name,
							@FormParam("stockQty") int qty,
							@FormParam("manufactYr") String manufact,
							@FormParam("latestRepairDate") String repair,
							@FormParam("createdTime") String created,
							@FormParam("updatedTime") String updated) {
		
		String output = invService.updateInventory(invID, new Inventory(invID,code,name,qty,manufact,repair,created,updated));
		return output;
	}
	
	//Delete
	@DELETE
	@Path("/Delete/{invID}")
	@Produces(MediaType.TEXT_PLAIN)
	public String removeInventory(@PathParam("invID") int invID) {
		String response = invService.deleteInventory(invID);
		return response;
	}
	
			
}
