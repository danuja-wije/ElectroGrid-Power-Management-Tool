package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;



public class LoginServiceImpl implements LoginService {
	private static final String USERNAME= "root";
	private static final String PASSWORD = "";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/electro_login_db";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	Connection connection = null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement = null;
	Statement statement = null;
	private String query = "";

	private Connection connect() throws SQLException {
		if(connection != null && !connection.isClosed()) {
			return connection;
		}
		else {
			try {
				Class.forName(DRIVER);
				connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				System.out.println("Successfully Connected Electro Main Database");


			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return connection;
	}


	@Override
	public boolean isAuthenticated(String auth) {

		String decodeString = "";
		String[] authParts = auth.split("\\s+");
		String authInfo = authParts[1];

		byte[] bytes = null;
		try {
			bytes =  Base64.getDecoder().decode(authInfo);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		// TODO Auto-generated method stub

		decodeString = new String(bytes);
		System.out.println(decodeString);

		String[] details = decodeString.split(":"); 

		String email = details[0];
		String password = details[1];

		return authenticate(email, password);
	}
	@Override
	public boolean authenticate(String userID,String password) {
		boolean output = false;
		String usr= "";
		String pss = "";
		
		System.out.println(userID);
		System.out.println(password);
		query = "SELECT * FROM `authdetails` WHERE `authdetails`.`userID` ='"+userID+"' AND `authdetails`.`password` ='"+password+"'";
		try {
			connection = connect();
			if (connection == null) {
				System.out.println("Error while connecting to the database for view Data");
			}

			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);

			

			while (resultSet.next()) {

				usr = resultSet.getString("email");
				pss = resultSet.getString("password");

			}
			
			if (usr.equals(userID) && pss.equals(password)) {
				output = true;
			}
			connection.close();
			query = "";

		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return output;

	}

}
