package controller;

import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/Login")
public class LoginController {
	@GET
	@Path("/Validate")
	@Produces(MediaType.TEXT_PLAIN)

	public String checkLogin() {
		return "success";
	}
}
