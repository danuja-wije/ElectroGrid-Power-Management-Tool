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
	
	//retrive inventory
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML)
	public String readItems() 
	{ 
		return invObj.readInventory(); 
	}
	
}
