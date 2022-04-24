package client;




import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class ClientManaget {
	
	
	Client client = ClientBuilder.newClient();
	
	String result = client.target("http://localhost:8080/Electro_Grid_Power_App/Validation/Login/Validate").request().get(String.class);
}
