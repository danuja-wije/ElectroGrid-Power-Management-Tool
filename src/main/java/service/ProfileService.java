package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Consumer;;

public class ProfileService {

	//Create objects for database Connection class
	private static boolean Success = false;
	private static Connection con = null;
	private static Statement stat = null;
	private static ResultSet rs = null;
	
	public static List<Consumer> viewProfile(String consumerId){
		
		ArrayList<Consumer> consumer = new ArrayList<>();
		
		try {
			
			con = controller.DBConneect.getConnection();
			stat = con.createStatement();
			
			//SQL Query			
			String sql = "select * from Consumer where consumerId = '"+consumerId+"'";
						
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
}
