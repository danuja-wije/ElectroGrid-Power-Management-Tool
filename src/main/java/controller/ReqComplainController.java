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

import model.ReqComplain;
import service.ReqComplainService;
import service.ReqComplainServiceImpl;


@Path("/Request")
public class ReqComplainController {
	
	//Request service
	ReqComplainService requestserv = new ReqComplainServiceImpl();
	ArrayList<ReqComplain> request = new ArrayList<>();;
	
	//Insert
	@POST
	@Path("/Insert")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertRequest(@FormParam("id") String id, @FormParam("cutomerID") String cutomerID
			,@FormParam("type") String type, @FormParam("description") String description) {
		String output = requestserv.insertRequest(new ReqComplain(id, cutomerID, type, description));
		return output;
	}
	
	//View
	@GET
	@Path("/Show/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String viewRequest(@PathParam("id") String id) {
		Gson gson = new Gson();
		request = requestserv.viewRequest(id);
		String jsonString  = gson.toJson(request);
		return jsonString;
	}
	
	//Delete
	@DELETE
	@Path("/Delete/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteRequest(@PathParam("id") String id) {
		String response = requestserv.deleteRequest(id);
		return response;
	}
	
	//Update
	@PUT
	@Path("/Update/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateRequest(@PathParam("id") String id , @FormParam("cutomerID") String cutomerID,
			@FormParam("type") String type, @FormParam("description") String description) {
		String output = requestserv.updateRequest(id, new ReqComplain(
				null, cutomerID, type, description));
		return output;
	}
}
