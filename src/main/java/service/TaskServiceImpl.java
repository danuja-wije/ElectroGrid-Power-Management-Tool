package service;

import java.nio.file.FileSystemAlreadyExistsException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Task;
import model.Worker;

public class TaskServiceImpl implements TaskService {
	
	private static final String USERNAME = "root";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/electrogriddb";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String PASSWORD = "";
	private static Connection connection = null;
	private static String query = "";
	private static PreparedStatement preparedStatement = null;
	private static Statement statement = null;
	private static ResultSet resultSet = null;
	private static Task task = null;
	private static Worker worker = null;
	private static List<Worker>worker_list = null;

	private Connection connect() throws SQLException {
		if (connection != null && !connection.isClosed()) {
			return connection;
		}
		else {
			try {
				Class.forName(DRIVER);
				connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				System.out.println("Successfully Connected");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return connection;
		}
	}
	public Task viewTask(int taskID) {
		//task attribute
		String output = "";
		String title = "";
		String desc = "";
		String handleBy = "";
		String status = "";
		String created ="";
		String lastUpdate ="";
		String customerID = "";
		
		//worker list
		worker_list = new ArrayList<Worker>();
		//worker attribute
		String workerID = "";
		String role = "";
		try {
			connection = connect();

			if(connection == null) {
				output = "Error while connecting to the database for inserting";
				return null;
			}

			query = "select * from task where taskID = "+taskID;
			String query_workers = "select * from task_workers where taskID = "+taskID;
			statement = connection.createStatement();

			resultSet = statement.executeQuery(query);


			while(resultSet.next()) {
				title = resultSet.getString("title");
				desc = resultSet.getString("description");
				handleBy = resultSet.getString("handleBy");
				status = resultSet.getString("status");
				created =resultSet.getString("createTime");
				lastUpdate = resultSet.getString("lastUpdate");
				customerID = resultSet.getString("customerID");
				
			}

			
			ResultSet worker_rs = statement.executeQuery(query_workers);


			while(worker_rs.next()) {
				workerID = worker_rs.getString("workerID");
				role = worker_rs.getString("role");
				
				worker = new Worker(taskID, workerID,role);
				worker_list.add(worker);
			}
			//create task object
			task = new Task(taskID, title, desc,customerID, worker_list, handleBy, created, lastUpdate);
			output="Sucess";


			connection.close();
			query = "";

		} catch (Exception e) {
			// TODO: handle exception
			task = new Task();
			output = "Error while reading tasks";
			System.err.println(e.getMessage());
		}
		task.setMessage(output);
		return task;
	}

	public String insertTask(Task task) {
		String output = "";

		try {
			connection = connect();
			if (connection == null ) {
				output = "Error while connectiong to the database for Inserting";
				return output;
			}
			query = "INSERT INTO `task` (`taskID`, `title`, `description`, `customerID`, `handleBy`, `status`, `createTime`, `lastUpdate`) VALUES (NULL, ?, ?, ?, ?, NULL, NULL, NULL)";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, task.getTitle());
			preparedStatement.setString(2, task.getDesc());
			preparedStatement.setString(3, task.getCustomerID());
			preparedStatement.setString(4, task.getHandleBy());
			preparedStatement.execute();

			connection.close();

			output = "Inserted Successfully";
			query = "";
		} catch (Exception e) {
			// TODO: handle exception
			output = "Error while inserting tasks";
			System.err.println(e.getMessage());
		}
		return output;
	}
	public String insertWorkers(Worker worker) {
		String output = "";
		try {
			connection  = connect();
			if (connection == null ) {
				output = "Error while connectiong to the database for Inserting";
				return output;
			}

			query = "INSERT INTO `task_workers` (`taskID`, `workerID`,`role`) VALUES (?, ? , ?)";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, worker.getTaskID());
			preparedStatement.setString(2, worker.getWorkerID());
			preparedStatement.setString(3, worker.getRole());

			preparedStatement.execute();

			connection.close();

			output = "Inserted Successfully";
			query = "";

		} catch (Exception e) {
			// TODO: handle exception
			output = "Error while inserting the workers";
			System.err.println(e.getMessage());
		}
		return output;
	}
	@Override
	public String updateWorker(Worker worker) {
		// TODO Auto-generated method stub
		String output = "";
		
		try {
			connection = connect();
			
			if (connection == null) {
				output = "Error while connectiong to the database for Updating";
				return output;
			}
			
			query = "UPDATE `task_workers` SET `role` = ? WHERE `taskID` = ? AND `workerID` = ?";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, worker.getRole());
			preparedStatement.setInt(2, worker.getTaskID());
			preparedStatement.setString(3,worker.getWorkerID());

			preparedStatement.execute();

			connection.close();

			output = "Update Successfully";
			query = "";
		} catch (Exception e) {
			// TODO: handle exception
			output = "Error while inserting the workers";
			System.err.println(e.getMessage());
		}

		return output;
	}
	@Override
	public String deleteWorler(int taskID, String workerID) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String output = "";

		try {
			if (connection == null) {
				output = "Error while connectiong to the database for Inserting";
				return output;
			}

			query = "DELETE FROM `task_workers` WHERE `task_workers`.`taskID` = ? AND `task_workers`.`workerID` = ?";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, taskID);
			preparedStatement.setString(2,workerID);

			preparedStatement.execute();

			connection.close();

			output = "Delete Successfully";
			query = "";
		} catch (Exception e) {
			// TODO: handle exception
			output = "Error while inserting the workers";
			System.err.println(e.getMessage());
		}

		return output;
	}
}
