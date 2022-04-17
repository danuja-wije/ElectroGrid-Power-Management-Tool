package controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.CreditCard;
import service.CreditCardService;
import service.CreditCardServiceImpl;

@Path("/Payment")
public class CreditCardController {

	//Card service
	CreditCardService cardService = new CreditCardServiceImpl();
	
	//Insert
	@POST
	@Path("/Card")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertWorker(@FormParam("user_ID") String userID , @FormParam("card_number") long cardNumber, @FormParam("ccv") int ccv
			,@FormParam("date") String date, @FormParam("name_on_card") String name, @FormParam("card_issuer") String issuer) {
		String output = cardService.insertCreditCard(new CreditCard(
				userID, cardNumber, ccv, date, name, issuer));
		return output;
	}
}
