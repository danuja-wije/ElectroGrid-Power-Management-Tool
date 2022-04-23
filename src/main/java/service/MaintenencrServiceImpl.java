package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Interruption;

public class MaintenencrServiceImpl implements MaintenanceService {

	private static final String USERNAME= "root";
	private static final String PASSWORD = "";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/electro_db";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	Statement statement = null;
	ResultSet resultSet = null;
	List<Interruption> list = null;
	Interruption interruption = null;
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
	public String insertInterruption(Interruption interruption) {
		// TODO Auto-generated method stub
		String output = "";

		try {
			connection = connect();

			if (connection == null) {
				output = "Error while conecting to the database for inserting";
				return output;
			}

			query = "INSERT INTO `interruption` (`intType`, `title`, `description`, `approval`, `interruptionStart`, `interruptionEnd`) VALUES (?,?,?,?,?,?)";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, interruption.getInType());
			preparedStatement.setString(2, interruption.getTitle());
			preparedStatement.setString(3, interruption.getDescription());
			preparedStatement.setString(4, interruption.getApproval());
			preparedStatement.setString(5, interruption.getInterruptionStartDate());
			preparedStatement.setString(6, interruption.getInterruptionEndDate());
			preparedStatement.execute();

			connection.close();

			output = "Interted Successfully";
			query = "";

		} catch (Exception e) {
			// TODO: handle exception
			output = "Error while inserting interruption";
			System.err.println(e.getMessage());
		}

		return output;
	}

	@Override
	public List<Interruption> allInterruptions() {
		// TODO Auto-generated method stub
		list =new ArrayList<Interruption>();
		query = "SELECT * FROM `interruption`";
		try {
			connection = connect();
			if (connection == null) {
				System.out.println("Error while connecting to the database for view Data");
			}

			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);



			while (resultSet.next()) {

				int id = resultSet.getInt("id");
				String inType = resultSet.getString("intType");
				String title = resultSet.getString("title");
				String description = resultSet.getString("description");
				String approval = resultSet.getString("approval");
				String timestamp = resultSet.getString("timestamp");
				String startDate = resultSet.getTimestamp("interruptionStart").toString();
				String endDate = resultSet.getTimestamp("interruptionEnd").toString();

				interruption = new Interruption(inType, title, description, startDate, endDate,null,approval);
				interruption.setId(id);
				interruption.setTimeStamp(timestamp);
				list.add(interruption);
			}
			connection.close();
			query = "";

		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return list;
	}

	@Override
	public String updateInterruption(Interruption interruption) {
		// TODO Auto-generated method stub
		String output = "";

		try {
			connection = connect();

			if (connection == null) {
				output = "Error while conecting to the database for Updating interruption";
				return output;
			}

			query = "UPDATE `interruption` SET `intType` = ?, `title` = ?, `description` =?, `approval` = ?, `interruptionStart` = ?, `interruptionEnd` = ? WHERE `interruption`.`id` = ?";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, interruption.getInType());
			preparedStatement.setString(2, interruption.getTitle());
			preparedStatement.setString(3, interruption.getDescription());
			preparedStatement.setString(4, interruption.getApproval());
			preparedStatement.setString(5, interruption.getInterruptionStartDate());
			preparedStatement.setString(6, interruption.getInterruptionEndDate());
			preparedStatement.setInt(7, interruption.getId());
			preparedStatement.execute();

			connection.close();

			output = "Updated Successfully";
			query = "";

		} catch (Exception e) {
			// TODO: handle exception
			output = "Error while inserting Updating";
			System.err.println(e.getMessage());
		}

		return output;
	}



	@Override
	public String updateEffectedCustomer(List<String>list) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public String deleteInterruption(int id) {
		// TODO Auto-generated method stub
		String output = "";

		try {
			connection = connect();

			if (connection == null) {
				output = "Error while conecting to the database for Deleting interruption";
				return output;
			}

			query = "DELETE FROM interruption WHERE `interruption`.`id` = ?";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, id);

			preparedStatement.execute();

			connection.close();

			output = "Delete Successfully";
			query = "";

		} catch (Exception e) {
			// TODO: handle exception
			output = "Error while inserting Deleting";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
