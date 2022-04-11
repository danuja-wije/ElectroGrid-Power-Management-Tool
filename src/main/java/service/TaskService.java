package service;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/Task")
public class TaskService {
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String TestTask() {
		return "Test Complete";
	}
}
