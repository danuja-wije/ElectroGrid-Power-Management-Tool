package controller;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*;

import model.Inventory;

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/Inventory") 
public class InventoryController {
	
	Inventory invObj = new Inventory();
	
	//View(retrieve) all inventory records
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML)
	public String readInventory() { 
		
		return invObj.readInventory(); 
	}
	//Tested and verified above View all operation through POSTMAN
	
	//View a record for a specific id 
	@GET
	@Path("/{invID}") 
	@Produces(MediaType.TEXT_HTML) 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String viewSelectCart(@PathParam("invID") int invID){
			
		return invObj.readspecificInventory(invID);
	}
	//Tested and verified above View a specific record operation through POSTMAN
	
	//Create(Insert) operation
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertInventory( @FormParam("invItemCode") String code,
								  @FormParam("invItemName") String name,
								  @FormParam("stockQty") String qty,
								  @FormParam("manufactYr") String manufact,
								  @FormParam("latestRepairDate") String repair){
		
			String output = invObj.insertInventory(code, name, qty, manufact, repair);
			return output;
	}
	//Tested and verified above Create operation through POSTMAN
	
	//Update operation
	@PUT
	 @Path("/")
	 @Produces(MediaType.TEXT_PLAIN)
	 public String updateDoctor(String invData) {
		
		 //Convert the input string to a JSON object
		 JsonObject docjObj = new JsonParser().parse(invData).getAsJsonObject();
		  
		//Read the values from the JSON object
		 String id= docjObj.get("invID").getAsString();
		 String code= docjObj.get("invItemCode").getAsString();
		 String name= docjObj.get("invItemName").getAsString();
		 String qty= docjObj.get("stockQty").getAsString();
		 String manufact = docjObj.get("manufactYr").getAsString();
		 String repair= docjObj.get("latestRepairDate").getAsString();
		  
		 String output= invObj.updateInventory(id, code,name,qty,manufact,repair);
		 return output;
		  	 
	  }
			
			
	
	
	


	
	
	
}
