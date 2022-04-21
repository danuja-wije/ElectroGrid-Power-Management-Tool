package model;

import java.sql.*; 

public class Inventory {
	
	private Connection connect() 
	{
		Connection con = null; 
	 
		try
		{ 
			Class.forName("com.mysql.jdbc.Driver"); 
	 
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogriddb", "root", ""); 
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	 
		return con; 
	}
	
	public String insertInventory(String code,String name, String qty, String manufact, String repair) 
	{ 
		String output = ""; 
	 
		try
		{ 
			Connection con = connect(); 
			
			if (con == null) 
			{
				return "Error while connecting to the database for inserting."; 
			} 
	 
			// create a prepared statement
			String query = " insert into inventory (`inventoryitemID`,`inventoryitemCode`,`inventoryitemName`,`stockQty`,`manufactYr`,`latestRepairYr`)"    
							+ " values (?, ?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	
			// binding values
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, code); 
			preparedStmt.setString(3, name); 
			preparedStmt.setDouble(4, Double.parseDouble(qty)); 
			preparedStmt.setString(5, manufact); 
			preparedStmt.setString(6, repair);
			
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Inserted successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while inserting the item."; 
			System.err.println(e.getMessage()); 
		} 
		
		return output; 
	 }
	
	public String readInventory() 
	{
		String output = ""; 
		
		try
		{ 
			Connection con = connect(); 
	 
			if (con == null) 
			{
				return "Error while connecting to the database for reading."; 
			} 
	 
			// Prepare the html table to be displayed
			output = "<table style='border:1px solid black;margin-left:auto;margin-right:auto;'><tr><th>Inventory Code</th><th>InventoryItem Name</th>" +
					"<th>Stock Quantity</th>" + 
					"<th>Manufacturing Year</th>" +
					"<th>Repairing Year</th>" +
					"</tr>"; 
	 
			String query = "select * from inventory"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
	 
			// iterate through the rows in the result set
			while (rs.next()) 
			{ 
				String id = Integer.toString(rs.getInt("inventoryitemID")); 
				String code = rs.getString("inventoryitemCode"); 
				String name = rs.getString("inventoryitemName"); 
				String qty = Double.toString(rs.getDouble("stockQty")); 
				String manufact = rs.getString("manufactYr"); 
				String repair = rs.getString("latestRepairYr");
	
				// Add into the html table
				output += "<tr><td>" + code + "</td>"; 
				output += "<td>" + name + "</td>"; 
				output += "<td>" + qty + "</td>"; 
				output += "<td>" + manufact + "</td>"; 
				output += "<td>" + repair + "</td>"; 
	 
				// buttons
				output += "<input name='inventoryitemID' type='hidden' value='" + id 
										+ "'>" + "</tr>"; 
			} 
			
			con.close(); 
	 
			// Complete the html table
			output += "</table>"; 
	 
		} 
		catch (Exception e) 
		{ 
			output = "Error while reading the items."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	}
	
	public String updateItem(String id, String code, String name, String qty,String manufact, String repair)
	{ 
		String output = ""; 
	 
		try
		{ 
			Connection con = connect(); 
	 
			if (con == null) 
			{
				return "Error while connecting to the database for updating."; 
			} 
	 
			// create a prepared statement
			String query = "UPDATE inventory SET inventoryitemCode=?,inventoryitemName=?,stockQty =?,manufactYr=?,latestRepairYr=? WHERE inventoryitemID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values
			preparedStmt.setString(1, code); 
			preparedStmt.setString(2, name); 
			preparedStmt.setDouble(3, Double.parseDouble(qty)); 
			preparedStmt.setString(4, manufact); 
			preparedStmt.setString(5, repair);
			preparedStmt.setInt(6, Integer.parseInt(id)); 
	 
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			
			output = "Updated successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while updating the item."; 
			System.err.println(e.getMessage()); 
		} 
		
		return output; 
	 }
	
	 public String deleteItem(String id) 
	 { 
		 String output = ""; 
	 
		 try
		 { 
	 
			 Connection con = connect(); 
	 
			 if (con == null) 
			 {
				return "Error while connecting to the database for deleting."; 
			} 
	 
			 // create a prepared statement
			 String query = "delete from inventory where inventoryitemID=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(id)); 
	 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Deleted successfully"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while deleting the item."; 
			 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	 }
}
