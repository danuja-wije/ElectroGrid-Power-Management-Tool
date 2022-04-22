package model;

import java.sql.*; 

public class Inventory {
	
	//Connect to DB
	public Connection connect(){
		
		Connection con = null;
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogriddb","root", "");
	       
			//For testing
			System.out.print("Successfully connected");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return con;
	}
	
	//Create a record for inventory service
	public String insertInventory(String code, String name, String qty, String manufact, String repair){
		
	    String output = "";
		
		try{
			
			Connection con = connect();
	        
			if (con == null){
				
				return "Error while connecting to the database";
	        }
	        
	        // create a prepared statement
	        String query = " INSERT INTO inventory(`invID`,`invItemCode`,`invItemName`,`stockQty`,`manufactYr`,`latestRepairDate`)" 
	        							+ " VALUES (?, ?, ?, ?, ?, ?)";
	        PreparedStatement preparedStmt = con.prepareStatement(query);

	        // binding values
	        preparedStmt.setInt(1, 0);
	        preparedStmt.setString(2, code);
	        preparedStmt.setString(3, name);
	        preparedStmt.setDouble(4,Double.parseDouble(qty)); 
	        preparedStmt.setString(5, manufact);
	        preparedStmt.setString(6, repair);
	      
	        //execute the statement
	        preparedStmt.execute();
	        con.close();

	        output = "Inserted successfully";

		}catch(Exception e){
			
			output = "Error while inserting";
	        System.err.println(e.getMessage());
		}

		return output;

	}
	
	//Retrieve all records for inventory service
	public String readInventory(){
		
		String output = "";
		
		try{
			
			Connection con = connect();
				
			if (con == null){
				return "Error while connecting to the database for reading."; 
			}
			
			// Prepare the html table to be displayed
			output = "<table style=\"border:1px solid black;margin-left:auto;margin-right:auto;\"><tr><th>Inventory ID</th>"
					+ "<th>Inventory Code</th><th>Inventory Item Name</th><th>Stock Quantity</th>"
						+ "<th>Manufacturing Year</th><th>Latest Repairing Date</th></tr>";
				
			String query = "SELECT * FROM inventory";
				
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
				
			// iterate through the rows in the result set
			while (rs.next()){
				
				String id = Integer.toString(rs.getInt("invID"));
				String code = rs.getString("invItemCode");
				String name = rs.getString("invItemName");
				String qty = Double.toString(rs.getDouble("stockQty"));
				String manufact = rs.getString("manufactYr");
				String repair = rs.getString("latestRepairDate");

				// Add into the html table
				output += "<tr><td>" + id + "</td>";
				output += "<td>" + code + "</td>";
				output += "<td>" + name + "</td>";
				output += "<td>" + qty + "</td>";
				output += "<td>" + manufact + "</td>";
				output += "<td>" + repair + "</td>";
				output += "</tr>";
			}
			
			con.close();
			
			// Complete the html table
			output += "</table>";
			
		}catch (Exception e){
			
			output = "Error while reading the values.";
			System.err.println(e.getMessage());
			
		}
		
		return output;
	}
	
	//Retrieve a specific record for inventory service
	public String readspecificInventory(int specificID){
		
		String output = "";
		
		try{
			
			Connection con = connect();
			
			if (con == null){
				
				return "Error while connecting to the database for reading."; 
			}

			// Prepare the html table to be displayed
			output = "<table style=\"border:1px solid black;margin-left:auto;margin-right:auto;\"><tr>"
					+ "<th>Inventory ID</th><th>Inventory Code</th><th>Inventory Item Name</th><th>Stock Quantity</th>"
						+ "<th>Manufacturing Year</th><th>Latest Repairing Date</th></tr>";
			
			String query = "SELECT * FROM inventory WHERE invID=" + specificID;
				
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
				
			// iterate through the rows in the result set
			while (rs.next()){
				
				String id = Integer.toString(rs.getInt("invID"));
				String code = rs.getString("invItemCode");
				String name = rs.getString("invItemName");
				String qty = Double.toString(rs.getDouble("stockQty"));
				String manufact = rs.getString("manufactYr");
				String repair = rs.getString("latestRepairDate");

				// Add into the html table
				output += "<tr><td>" + id + "</td>";
				output += "<td>" + code + "</td>";
				output += "<td>" + name + "</td>";
				output += "<td>" + qty + "</td>";
				output += "<td>" + manufact + "</td>";
				output += "<td>" + repair + "</td>";
				output += "</tr>";
			}
			
			con.close();
				
			// Complete the html table
			output += "</table>";
			
		}catch (Exception e){
			
			output = "Error while reading the values.";
			System.err.println(e.getMessage());
			
		}
		
		return output;
	}
	
	
	public String updateInventory(String id, String code, String name, String qty, String manufact, String repair){
		
	    String output = "";

	    try{
	    	
	    	Connection con = connect();
	           
	    	if (con == null){
	    		
	           return "Error while connecting to the database for updating.";
	        }
	           
	        // create a prepared statement

	        String query = "UPDATE inventory SET invItemCode=?,invItemName=?,stockQty=?,manufactYr=?,latestRepairDate=? WHERE invID=?";
	        PreparedStatement preparedStmt = con.prepareStatement(query);

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
	           
	    }catch(Exception e){
	    	
	    	output = "Error while updating the value.";
			System.err.println(e.getMessage());
			
	    }
	    
	    return output;

	 }
	
	
	
	
	
	
	
	
	
	
	
	
}
