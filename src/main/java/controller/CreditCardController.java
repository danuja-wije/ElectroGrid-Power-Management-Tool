package controller;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import model.CreditCard;
import service.CreditCardService;
import service.CreditCardServiceImpl;

@Path("/Payment")
public class CreditCardController {

	//Card service
	CreditCardService cardService = new CreditCardServiceImpl();
	
	ArrayList<CreditCard> cards= new ArrayList<>();;
	
	
	//Insert
	@POST
	@Path("/Card")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertWorker(@FormParam("user_ID") String userID , @FormParam("card_number") long cardNumber, @FormParam("cvv") int cvv
			,@FormParam("date") String date, @FormParam("name_on_card") String name, @FormParam("card_issuer") String issuer) {
		String output = cardService.insertCreditCard(new CreditCard(
				userID, cardNumber, cvv, date, name, issuer));
		return output;
	}
	
	
	//View
	@GET
	@Path("/{user_ID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String viewCards(@PathParam("user_ID") String UID) {
		Gson gson = new Gson();
		cards = cardService.viewCards(UID);
		String jsonString  = gson.toJson(cards);
		return jsonString;
	}
}
