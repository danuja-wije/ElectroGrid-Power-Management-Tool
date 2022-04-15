package model;

public class Worker {
	private int taskID;

	private String workerID;

	public int getTaskID() {
		return taskID;
	}

	public String getWorkerID() {
		return workerID;
	}

	public Worker(int taskID, String workerID) {
		super();
		this.taskID = taskID;
		this.workerID = workerID;
	}
	
}
