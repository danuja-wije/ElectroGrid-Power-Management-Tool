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

import model.Payment;
import service.PaymentService;
import service.PaymentServiceImpl;

@Path("/Payment")
public class PaymentController {
	
		//Payment service
		PaymentService paymentService = new PaymentServiceImpl();
		
		ArrayList<Payment> payments= new ArrayList<>();;
		
		
		//Insert
		@POST
		@Path("/New")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertPayment(@FormParam("user_ID") String user_ID , @FormParam("bill_ID") int bill_ID, @FormParam("amount") float amount
				,@FormParam("card_number") long card_number) {
			String output = paymentService.insertPayment(new Payment(
					user_ID, bill_ID, amount, card_number));
			return output;
		}
		
		
		//View
		@GET
		@Path("/{user_ID}")
		@Produces(MediaType.APPLICATION_JSON)
		public String viewPayment(@PathParam("user_ID") String UID) {
			Gson gson = new Gson();
			payments = paymentService.getPayments(UID);
			String jsonString  = gson.toJson(payments);
			return jsonString;
		}
		

}
