package controller;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import service.LoginService;
import service.LoginServiceImpl;


@Path("/Login")
public class LoginController {
	LoginService loginService = new LoginServiceImpl();
	@GET
	@Path("/Validate")
	@Produces(MediaType.TEXT_PLAIN)
	public String checkLogin(@HeaderParam("Authorization") String auth) {
		
		if(loginService.isAuthenticated(auth)) {
			return "True";
		}else {
			return "False";
		}
	}
	
	@GET
	@Path("/User")
	@Produces(MediaType.TEXT_PLAIN)
	public String getUser(@HeaderParam("Authorization") String auth) {
		return loginService.getCurrentUser(auth);
	}
}
