package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import model.CreditCard;


public class CreditCardServiceImpl implements CreditCardService{
	
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
	
	//Card model
	private static CreditCard card;
	
	
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
	
	//Insert card
	@Override
	public String insertCreditCard(CreditCard card) {
		String output = "";
		try {
			connection  = connect();
			if (connection == null ) {
				output = "Error while connectiong to the database for Inserting";
				return output;
			}

			//Query
			query = "INSERT INTO `credit_cards` (`user_ID`, `card_number`, `ccv`, `exp_date`, `name_on_card`, `card_issuer`)"
					+ " VALUES (?, ?, ?, ?, ?, ?)";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, card.getUser_ID());
			preparedStatement.setLong(2, card.getCard_number());
			preparedStatement.setInt(3, card.getCcv());
			preparedStatement.setString(4, card.getDate());
			preparedStatement.setString(5, card.getName_on_card());
			preparedStatement.setString(6, card.getCard_issuer());
			
			preparedStatement.execute();

			connection.close();

			output = "Inserted Successfully";
			query = "";

		} catch (Exception e) {
			// TODO: handle exception
			output = "Error while inserting the credit card";
			System.err.println(e.getMessage());
		}
		return output;
	}


	@Override
	public String deleteCard(long cardNumber) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public CreditCard viewCard() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String updateCard(CreditCard card) {
		// TODO Auto-generated method stub
		return null;
	}
}
