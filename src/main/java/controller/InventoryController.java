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
	
	//retrieve inventory items
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML)
	public String readInventory() 
	{ 
		return invObj.readInventory(); 
	}
	
	//Retrieve details for a specific inventory  
	@GET
	@Path("/Items/{inventoryitemCode}") 
	@Produces(MediaType.TEXT_HTML) 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String viewSelectInventory(@PathParam("inventoryitemCode") int code){
				
		return invObj.retrieveSelectInventory(code);
	
	}
			
			
	
	
	


	
	
	
}
