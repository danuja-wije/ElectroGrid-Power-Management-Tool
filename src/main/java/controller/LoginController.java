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
	
	public String checkLogin(@HeaderParam("authentication") String auth) {
		String output = "";
		if(loginService.isAuthenticated(auth)) {
			output = "Login Successfull";
		}else {
			output = "Login Failed";
		}
		return output;
	}
}
