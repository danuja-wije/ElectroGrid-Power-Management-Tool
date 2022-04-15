package service;

import model.Task;

public interface TaskService {
	public Task viewTask(int taskID);
	public String insertTask(Task task);
	public String insertWorkers(String taskID,String workerID);
}
