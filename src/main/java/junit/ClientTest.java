package junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import client.ClientManager;

class ClientTest {
	final String TOKEN = "Basic RUdFTjE6MTIzNA=="; //basic auth token 
	
	//testing user Login
	@Test
	void testLogin() {
		ClientManager clientManager = new ClientManager();
		
		String result = clientManager.getCurrentUser(TOKEN); 
		assertEquals("EGEN1", result);
	}

}
