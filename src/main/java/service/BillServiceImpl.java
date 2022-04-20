package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Bill;

public class BillServiceImpl implements BillService{

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
	
	private static ArrayList<Bill> billList = null;
	String output = "";
	
	//Bill model
	private static Bill bill;
	
	
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
	
	@Override
	public String generateBill(Bill bill) {
		
		try {
			//Connection
			connection = connect();
			
			if(connection == null) {
				output = "Error connecting to DB";
				return output;
			}
			
//			private String user_ID;
//			private String bill_ID;
//			private String month;
//			private String date_created;
//			private float units;
//			private float unit_price;
//			private float charge;
			
			//Query
			query = "INSERT INTO `bills` (`user_ID`, `bill_ID`, `month`, `units`, `unit_price`, `charge`, `date_created,`)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?)";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, bill.getUser_ID());
			preparedStatement.setString(2, bill.getBill_ID());
			preparedStatement.setString(3, bill.getMonth());
			preparedStatement.setString(4, bill.getDate_created());
			preparedStatement.setFloat(5, bill.getUnits());
			preparedStatement.setFloat(6, bill.getUnit_price());
			preparedStatement.setFloat(7, bill.getCharge());
			
			preparedStatement.execute();
			connection.close();

			output = "Inserted Successfully";
			query = "";
			
			
		}catch(Exception e) {
			System.err.print(e.getMessage());
		}
		
		return output;
	}

	//Delete
	@Override
	public String deleteBill(long bill_ID) {
		//Connection
		try {
			connection = connect();
			
			if(connection == null) {
				output = "Error while connectiong to the database";
				return output;
			}
			
			//Query
			query = "DELETE FROM bills WHERE bill_ID = " + bill_ID;
			statement = connection.createStatement();
			int del = statement.executeUpdate(query);
			
			if(del > 0) {
				output = "Bill removed";
			}
			else {
				output = "Bill not found";
			}
			
		}catch(Exception e) {
			output = "Error deleting data " + e.getMessage();
			System.err.println(output);
			return output;
		}
		return output;
	}

	@Override
	public ArrayList<Bill> viewAllBills(String user_ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Bill> viewMonthlyBills(String user_ID, String Month) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateBill(String bill_ID, Bill bill) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
