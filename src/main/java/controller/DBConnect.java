package controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {

		private static String URL = "jdbc:mysql://localhost:3306/company";
		private static String USERNAME ="root";
		private static String PASSWORD = "Nmhs1055330+";
		private static String DRIVER = "com.mysql.jdbc.Driver";
		private static Connection con;
		
		public static Connection getConnection() {
			
			try {
				
				Class.forName("com.mysql.jdbc.Driver");
				
				con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				System.out.println("Successfully Connected...");
				
			}catch(Exception e) {
				System.out.println("Database connection is not success...");
			}
			
			return con;
		}
}
