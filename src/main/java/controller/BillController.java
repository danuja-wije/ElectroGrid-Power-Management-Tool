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

import model.Bill;
import service.BillService;
import service.BillServiceImpl;

public class BillController {

	//Card service
		BillService billService = new BillServiceImpl();
		
		ArrayList<Bill> bills= new ArrayList<>();;
	
		
		//Insert
		@POST
		@Path("/Generate_bill")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertCard(@FormParam("user_ID") String userID , @FormParam("year") int year, @FormParam("month") String month
				,@FormParam("date_created") String date_created, @FormParam("units") float units, @FormParam("unit_price") float unit_price, @FormParam("charge") float charge) {
			String output = billService.generateBill(new Bill(userID, year, month, date_created, units, unit_price, charge));
			return output;
		}
		
		
		//View All Bills
		@GET
		@Path("/{user_ID}")
		@Produces(MediaType.APPLICATION_JSON)
		public String viewAllBills(@PathParam("user_ID") String UID) {
			Gson gson = new Gson();
			bills = billService.viewAllBills(UID);
			String jsonString  = gson.toJson(bills);
			return jsonString;
		}
		
		
		//View Monthly Bills
		@GET
		@Path("/{user_ID}/{year}/{month}")
		@Produces(MediaType.APPLICATION_JSON)
		public String viewMonthlyBills(@PathParam("user_ID") String UID, @PathParam("year") int year, @PathParam("month") String month ) {
			Gson gson = new Gson();
			bills = billService.viewMonthlyBills(UID, year, month);
			String jsonString  = gson.toJson(bills);
			return jsonString;
		}
		
		
		//Delete
		@DELETE
		@Path("/Deletebill/{bill_ID}")
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteCards(@PathParam("bill_ID") String bill_ID) {
			String response = billService.deleteBill(bill_ID);
			return response;
		}
		
		
		//Update bill
		@PUT
		@Path("/Updatebill/{bill_ID}")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateCard(@PathParam("bill_ID") String bill_ID , @FormParam("date_created") String date_created, @FormParam("units") float units
				,@FormParam("unit_price") float unit_price, @FormParam("charge") float charge) {
			String output = billService.updateBill(bill_ID, new Bill(date_created, units, unit_price, charge));
			return output;
		}
}
