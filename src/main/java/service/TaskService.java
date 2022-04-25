package service;

import model.Task;
import model.Worker;

public interface TaskService {
	public Task viewTask(int taskID);
	public String insertTask(Task task);
	public String insertWorkers(Worker worker);
	public String updateWorker(Worker worker);
	public String deleteWorler(int taskID,String workerID);
}
