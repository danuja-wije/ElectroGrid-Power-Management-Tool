package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Consumer;
import utill.DBConnect;

public class ProfileService {

	//Create objects for database Connection class
	private static boolean Success = false;
	private static Connection con = null;
	private static Statement stat = null;
	private static ResultSet rs = null;
	
	//Read Consumer Profile Data
	public static List<Consumer> viewProfile(String userName){
		
		ArrayList<Consumer> consumer = new ArrayList<>();
		
		try {
			
			con = DBConnect.getConnection();
			stat = con.createStatement();
			
			//SQL Query			
			String sql = "select * from consumer where username = '"+userName+"'";
						
			rs= stat.executeQuery(sql);
			
			while(rs.next()) {
				String conId = rs.getString(1);
				String name = rs.getString(2);
				String address = rs.getString(3);
				String nic = rs.getString(4);
				String mobile = rs.getString(5);
				String email = rs.getString(6);
				String username = rs.getString(7);
				String password = rs.getString(8);
			
				//Create Employee Object
				Consumer c = new Consumer(conId, name, address, nic, mobile, email, username, password);
				consumer.add(c);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return consumer;
	}
	
	//Insert Consumer Profile Details
	public static boolean insertProfileDetails(String name, String address, String mobile, String nic, String email, String username, String  password) {
	    
    	boolean isSuccess = false;
    	
    	try {
    		con = DBConnect.getConnection();
    		stat = con.createStatement();
    	    String sql = "insert into consumer values (0, '"+name+"', '"+address+"', '"+mobile+"', '"+nic+"','"+email+"','"+username+"', '"+password+"')";
    		int rs = stat.executeUpdate(sql);
    		
    		if(rs > 0) {
    			isSuccess = true;
    		} else {
    			isSuccess = false;
    		}
    		
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
 	
    	return isSuccess;
    }
	
	//Update Consumer Profile Details
	public static boolean updateProfileDetails(String conId, String name, String address, String mobile, String nic, String email, String username, String  password) {
		
    	try {
    		con = DBConnect.getConnection();
    		stat = con.createStatement();
    		String sql = "update consumer set name='"+name+"',address='"+address+"',mobile='"+mobile+"',nic='"+nic+"', email='"+email+"',"
    				+ "username='"+username+"',password='"+password+"'"+ "where consumerId='"+conId+"'";
    		int rs = stat.executeUpdate(sql);
    		
    		if(rs > 0) {
    			Success = true;
    		}
    		else {
    			Success = false;
    		}
    		
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return Success;
    }
	
	//Delete Profile
	public static boolean deleteProfile(String conId) {
		
		int convertID = Integer.parseInt(conId);
		
		//Validate
		try {		
			con = DBConnect.getConnection();
			stat = con.createStatement();
					
			//SQL Query			
			String sql = "delete from consumer where consumerId ='"+convertID+"'";
					
					//Run SQL Query
					int a = stat.executeUpdate(sql); 
					
					if(a > 0) {
						Success = true;
					}
					else {
						Success = false;
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
		
		return Success;
	}
}
