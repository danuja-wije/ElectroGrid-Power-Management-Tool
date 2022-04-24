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
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/consumerdb";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String PASSWORD = "";
	private static Connection connection = null;
	private static String query = "";
	private static PreparedStatement preparedStatement = null;
	private static Statement statement = null;
	private static ResultSet resultSet = null;

	private static ArrayList<Bill> billList = null;
	String output = "";


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

	//Insert
	@Override
	public String generateBill(Bill bill) {

		try {
			//Connection
			connection = connect();

			if(connection == null) {
				output = "Error connecting to DB";
				return output;
			}

			//Query
			query = "INSERT INTO `bills` (`user_ID`, `account_ID`, `year`, `month`, `units`, `unit_price`, `charge`)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?)";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, bill.getUser_ID());
			preparedStatement.setString(2, bill.getAccount_ID());
			preparedStatement.setInt(3, bill.getYear());
			preparedStatement.setString(4, bill.getMonth());
			preparedStatement.setFloat(5, bill.getUnits());
			preparedStatement.setFloat(6, bill.getUnit_price());
			preparedStatement.setFloat(7, bill.getCharge());

			preparedStatement.execute();
			connection.close();

			output = "Inserted Successfully";
			query = "";


		}catch(Exception e) {
			output = e.getLocalizedMessage();
			System.err.print(output);
			return output;
		}

		return output;
	}


	//Delete
	@Override
	public String deleteBill(int bill_ID) {
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



	//Get all bills
	@Override
	public ArrayList<Bill> viewAllBills(String user_ID, String account_ID) {

		//Bill attribute
		int bill_ID = 0;
		int year = 0;
		String month = "";
		String date_created = "";
		float units = 0;
		float unit_price = 0;
		float charge = 0;


		//Bill List
		billList = new ArrayList<Bill>();

		//Connection
		try {
			connection = connect();

			if(connection == null) {
				System.err.println("Error while connecting to the database");
				return null;
			}

			//Query
			query = "SELECT * FROM bills WHERE user_ID = " + user_ID + " AND account_ID = " + account_ID;

			//Execute
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);

			//Get all results
			while(resultSet.next()) {
				bill_ID = resultSet.getInt("bill_ID");
				year = resultSet.getInt("year");
				month = resultSet.getString("month");
				date_created = resultSet.getString("date_created");
				units = resultSet.getFloat("units");
				unit_price = resultSet.getFloat("unit_price");
				charge = resultSet.getFloat("charge");

				//Add to list
				billList.add(new Bill(bill_ID, year, month, date_created, units, unit_price, charge ));
			}

		}catch(Exception e) {
			System.err.println("Error getting data " + e.getMessage());
		}

		return billList;
	}


	//Get bill by year and month
	@Override
	public ArrayList<Bill> viewMonthlyBills(String user_ID, int year, String month) {
		//Bill attribute
		int bill_ID = 0;
		String date_created = "";
		float units = 0;
		float unit_price = 0;
		float charge = 0;


		//Bill List
		billList = new ArrayList<Bill>();

		//Connection
		try {
			connection = connect();

			if(connection == null) {
				System.err.println("Error while connecting to the database");
				return null;
			}

			//Query
			query = "SELECT * FROM bills WHERE user_ID = " + user_ID + "AND year = " + year + "AND month = " + month;

			//Execute
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);

			//Get all results
			while(resultSet.next()) {
				bill_ID = resultSet.getInt("bill_ID");
				date_created = resultSet.getString("date_created");
				units = resultSet.getFloat("units");
				unit_price = resultSet.getFloat("unit_price");
				charge = resultSet.getFloat("charge");

				//Add to list
				billList.add(new Bill(bill_ID, year, month, date_created, units, unit_price, charge ));
			}

		}catch(Exception e) {
			System.err.println("Error getting data " + e.getMessage());
		}

		return billList;
	}


	//Update bill
	@Override
	public String updateBill(int bill_ID, Bill bill) {

		try {
			connection  = connect();
			if (connection == null ) {
				output = "Error while connectiong to the database";
				return output;
			}

			//Query
			query = "UPDATE bills SET units = ?, unit_price = ?, charge = ? WHERE bill_ID = " + bill_ID;

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setFloat(1, bill.getUnits());
			preparedStatement.setFloat(2, bill.getUnit_price());
			preparedStatement.setFloat(3, bill.getCharge());


			preparedStatement.executeUpdate();

			connection.close();

			output = "Updated Successfully";
			query = "";

		} catch (Exception e) {
			output = "Error while updating the credit card";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
