package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Payment;

public class PaymentServiceImpl implements PaymentService{

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

	private static ArrayList<Payment> paymentList = null;
	String output = "";

	//Payment model
	private static Payment payment;


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
	public String insertPayment(Payment payment){

		try {
			//Connection
			connection = connect();

			if(connection == null) {
				output = "Error connecting to DB";
				return output;
			}

			//Query
			query = "INSERT INTO `payments` (`user_ID`, `bill_ID`, `amount`, `card_number`)"
					+ " VALUES (?, ?, ?, ?)";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, payment.getUser_ID());
			preparedStatement.setInt(2, payment.getBill_ID());
			preparedStatement.setFloat(3, payment.getAmount());
			preparedStatement.setLong(4, payment.getCard_number());

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


	@Override
	public ArrayList<Payment> getPayments(String UID) {
		//payment attribute
		String user_ID  = "";
		int bill_ID = 0;
		String transaction_date = "";
		float amount = 0;
		long card_number = 0;
		long payment_ID = 0;


		//Payment List
		paymentList = new ArrayList<Payment>();

		//Connection
		try {
			connection = connect();

			if(connection == null) {
				System.err.println("Error while connecting to the database");
				return null;
			}

			//Query
			query = "SELECT * FROM payments WHERE user_ID = " + UID;

			//Execute
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);

			//Get all results
			while(resultSet.next()) {
				user_ID = resultSet.getString("user_ID");
				bill_ID = resultSet.getInt("bill_ID");
				transaction_date = resultSet.getString("transaction_date");
				amount = resultSet.getFloat("amount");
				card_number = resultSet.getLong("card_number");
				payment_ID = resultSet.getLong("payment_ID");

				//Add to list
				paymentList.add(new Payment(user_ID, bill_ID, transaction_date, amount, card_number, payment_ID));
			}

		}catch(Exception e) {
			System.err.println("Error getting data " + e.getMessage());
		}

		return paymentList;
	}
}
