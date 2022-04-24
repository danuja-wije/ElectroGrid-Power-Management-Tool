package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.ReqComplain;

public class ReqComplainServiceImpl implements ReqComplainService{
	
	//DB parameters
	private static final String USERNAME = "root";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/electrogriddb";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String PASSWORD = "";
	private static Connection connection = null;
	private static String query = "";
	private static PreparedStatement preparedStatement = null;
	private static Statement statement = null;
	private static ResultSet resultSet = null;
	
	private static ArrayList<ReqComplain> requestList = null;
	String output = "";
	
	//ReqComplain model
	private static ReqComplain request;
	
	//Connection
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
	
	//Insert request
	@Override
	public String insertRequest(ReqComplain request) {
		
		try {
			connection  = connect();
			if (connection == null ) {
				output = "Error while connectiong to the database";
				return output;
			}

			//Query
			query = "INSERT INTO `requests` (`id`, `cutomerID`, `type`, `description`)"
					+ " VALUES (?, ?, ?, ?)";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, request.getId());
			preparedStatement.setString(2, request.getCutomerID());
			preparedStatement.setString(3, request.getType());
			preparedStatement.setString(4, request.getDescription());
			
			preparedStatement.execute();

			connection.close();

			output = "Inserted Successfully";
			query = "";

		} catch (Exception e) {
			output = "Error while inserting the request";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//View Requests
	@Override
	public ArrayList<ReqComplain> viewRequest(String id) {
		//Request attributes
		String customerID = "";
		String type = "";
		String description = "";
		
		//Request List
		requestList = new ArrayList<ReqComplain>();
		
		//Connection
		try {
			connection = connect();
			
			if(connection == null) {
				System.err.println("Error while connecting to the database");
				return null;
			}
			
			//Query
			query = "SELECT * FROM requests WHERE id = " + id;
			
			//Execute
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			
			//Get all results
			while(resultSet.next()) {
				id = resultSet.getString("id");
				customerID = resultSet.getString("customerID");
				type = resultSet.getString("type");
				description = resultSet.getString("description");
				
				//Add to list
				requestList.add(new ReqComplain(id, customerID, type, description));
			}
			
		}catch(Exception e) {
			System.err.println("Error getting data " + e.getMessage());
		}
		
		return requestList;
	}
	
	//Update request
	@Override
	public String updateRequest(String id, ReqComplain request) {
		try {
			connection  = connect();
			if (connection == null ) {
				output = "Error while connectiong to the database";
				return output;
			}

			//Query
			query = "UPDATE requests SET customerID = ?, type = ?, description = ? WHERE id = " + id;

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, request.getCutomerID());
			preparedStatement.setString(2, request.getType());
			preparedStatement.setString(3, request.getDescription());
			
			preparedStatement.executeUpdate();

			connection.close();

			output = "Updated Successfully";
			query = "";

		} catch (Exception e) {
			output = "Error while updating the request";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//Delete request
	@Override
	public String deleteRequest(String id) {
		//Connection
		try {
			connection = connect();
			
			if(connection == null) {
				output = "Error while connectiong to the database";
				return output;
			}
			
			//Query
			query = "DELETE FROM requests WHERE id = " + id;
			statement = connection.createStatement();
			int del = statement.executeUpdate(query);
			
			if(del > 0) {
				output = "Request removed";
			}
			else {
				output = "Request not found";
			}
			
		}catch(Exception e) {
			output = "Error deleting data " + e.getMessage();
			System.err.println(output);
			return output;
		}
		return output;
	}

}
