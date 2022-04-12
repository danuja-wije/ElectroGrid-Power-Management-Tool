package model;

import java.sql.*;

public class Task {
	private static final String USERNAME = "root";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/electrogriddb";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String PASSWORD = "";
	private static Connection connection = null;
	private static String query = "";
	private static PreparedStatement preparedStatement = null;
	private static Statement statement = null;
	private static ResultSet resultSet = null;
	
	
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
	public String viewTask(int taskID) {
		String output = "";
		
		try {
			connection = connect();
			
			if(connection == null) {
				output = "Error while connecting to the database for inserting";
				return output;
			}
			
			query = "select * from task where taskID = "+taskID;
			String query_workers = "select * from task_workers where taskID = "+taskID;
			statement = connection.createStatement();
	
			resultSet = statement.executeQuery(query);
			

			while(resultSet.next()) {
				String title = resultSet.getString("title");
				String desc = resultSet.getString("description");
				String handleBy = resultSet.getString("handleBy");
				String status = resultSet.getString("status");
				String created =resultSet.getString("createTime");
				String lastUpdate = resultSet.getString("lastUpdate");
				output =  "<label>Task Title :"+title+"</label>" + "</br>"
						+ "<label>Task Description :"+desc+"</label>" + "</br>"
						+ "<label>Handle BY :"+handleBy+"</label>" + "</br>"
						+"<label>Status :"+status+"</label>" + "</br>"
						+ "<label>Created Date :"+created+"</label>" + "</br>"
						+"<label>Last Update :"+lastUpdate+"</label>" + "</br>";
			}
			
			ResultSet worker_rs = statement.executeQuery(query_workers);
			
			output  +=  "<table border='1'><tr><th>Worker ID</th><th>Action</th>" +
					"</tr>";

			while(worker_rs.next()) {
				String workerID = worker_rs.getString("workerID");
				output +=  "<tr><td>"+workerID+"</td>"
								 + "<td><form method='post' action='items.jsp'>"
								 + "<input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'>"
								 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
								 + "<input name='itemID' type='hidden' value='" + workerID 
								 + "</form></td></tr>"; 
			}
			
			output += "</table>";
			
			
			connection.close();
			query = "";
			
		} catch (Exception e) {
			// TODO: handle exception
			output = "Error while reading tasks";
			System.err.println(e.getMessage());
		}
		return output;
	}
	

	public String insertWorkers(String taskID,String workerID) {
		String output = "";
		try {
			connection  = connect();
			if (connection == null ) {
				output = "Error while connectiong to the database for Inserting";
				return output;
			}

			query = "INSERT INTO `task_workers` (`taskID`, `workerID`) VALUES (?, ?)";
			
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, taskID);
			preparedStatement.setString(2, workerID);
			
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
}
