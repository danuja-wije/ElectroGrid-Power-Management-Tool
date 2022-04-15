package controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Task;
import service.TaskService;
import service.TaskServiceImpl;

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
public class TaskController {
	TaskService taskService = new TaskServiceImpl();
	Task task = null;
	@GET
	@Path("/{taskID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String viewTask(@PathParam("taskID") int taskID) {
		Gson gson = new Gson();
		task = taskService.viewTask(taskID);
		String jsonString  = gson.toJson(task);
		return jsonString;
	}
	
	
	@POST
	@Path("/Worker")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertWorker(@FormParam("taskID") String taskID , @FormParam("workerID") String workerID) {
		System.out.println("Task ID :"+taskID);
		System.out.println("Worker ID :"+workerID);
		String output = taskService.insertWorkers(taskID, workerID);
		return output;
	}
	
//	@POST
//	@Path("/")
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	@Produces(MediaType.TEXT_PLAIN)
//	public String insertTask(@FormParam("title") String title,@FormParam("description") String description,@FormParam("")) {
//		
//	}
//	
}
