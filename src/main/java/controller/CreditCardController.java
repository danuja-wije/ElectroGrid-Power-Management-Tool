package controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Locale;

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
import com.google.protobuf.TextFormat.ParseException;

import model.CreditCard;
import service.CreditCardService;
import service.CreditCardServiceImpl;


@Path("/CrecitCard")
public class CreditCardController {


	//Card service
	CreditCardService cardService = new CreditCardServiceImpl();

	ArrayList<CreditCard> cards= new ArrayList<>();

	SimpleDateFormat sdfrmt = new SimpleDateFormat("MM/dd/yyyy");


	//Insert
	@POST
	@Path("/New")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertCard(@FormParam("user_ID") String userID , @FormParam("card_number") String cardNumber, @FormParam("cvv") int cvv
			,@FormParam("date") String date, @FormParam("name_on_card") String name, @FormParam("card_issuer") String issuer) {

		String output = "";
		sdfrmt.setLenient(false);

		//Validate date
		if(date.length() > 0){
			try
			{
				Date javaDate = (Date) sdfrmt.parse(date); 
			}
			/* Date format is invalid */
			catch (Exception e)
			{
				output += " Invalid Date format";
				return output;
			}
		}else {
			output += " Invalid Date";
		}

		//Validate card number
		if(cardNumber.length() > 19 || cardNumber.length() == 0){
			output += " Invalid card Number";
		}

		//Validate cvv
		if(cvv == 0 || cvv > 9999) {
			output += " Invalid CVV";
		}

		//Validate Name on card
		if(name.length() == 0){
			output += " Invalid Name";
		}

		//Validate card issuer
		if(issuer.length() == 0) {
			output += " Invalid Issuer";
		}

		//Execute Insert
		if(output == "") {
			output = cardService.insertCreditCard(new CreditCard(
					userID, cardNumber, cvv, date, name, issuer));
		}

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

	//Delete
	@DELETE
	@Path("/{card_number}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCards(@PathParam("card_number") String CardNum) {
		String response = cardService.deleteCard(CardNum);
		return response;
	}


	//Update
	@PUT
	@Path("/Updatecard/{old_card}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCard(@PathParam("old_card") String old_card , @FormParam("card_number") String cardNumber, @FormParam("cvv") int cvv
			,@FormParam("date") String date, @FormParam("name_on_card") String name, @FormParam("card_issuer") String issuer) {

		sdfrmt.setLenient(false);
		String output = "";

		//Validate date
		if(date.length() > 0){
			try
			{
				Date javaDate = (Date) sdfrmt.parse(date); 
			}
			/* Date format is invalid */
			catch (Exception e){
				output += " Invalid Date format";
				return output;
			}
		}else {
			output += " Invalid Date";
		}

		//Validate card number
		if(cardNumber.length() > 19 || cardNumber.length() == 0){
			output += " Invalid card Number";
		}

		//Validate cvv
		if(cvv == 0 || cvv > 9999){
			output += " Invalid CVV";
		}

		//Validate Name on card
		if(name.length() == 0){
			output += " Invalid Name";
		}

		//Validate card issuer
		if(issuer.length() == 0){
			output += " Invalid Issuer";
		}

		if(output == "") {
			output = cardService.updateCard(old_card, new CreditCard(
					null, cardNumber, cvv, date, name, issuer));
		}

		return output;
	}
}
