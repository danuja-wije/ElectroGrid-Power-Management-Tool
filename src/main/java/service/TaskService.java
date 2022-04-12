package service;
//load Models
import model.Task;
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
	
	Task task = new Task();
	
	@GET
	@Path("/{taskID}")
	@Produces(MediaType.TEXT_HTML)
	public String viewTask(@PathParam("taskID") int taskID) {
		return task.viewTask(taskID);
	}
	
	
	
	
	
	@POST
	@Path("/Worker")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertWorker(@FormParam("taskID") String taskID , @FormParam("workerID") String workerID) {
		System.out.println("Task ID :"+taskID);
		System.out.println("Worker ID :"+workerID);
		String output = task.insertWorkers(taskID, workerID);
		return output;
	}
	
	
}
