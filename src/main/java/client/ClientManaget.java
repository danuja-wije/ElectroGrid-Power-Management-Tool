package client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ClientManaget {
	
	
	Client client = ClientBuilder.newClient();
	
	String result = client.target("http://localhost:8080/Electro_Grid_Power_App/Validation/Login/Validate").request().get(String.class);
}
