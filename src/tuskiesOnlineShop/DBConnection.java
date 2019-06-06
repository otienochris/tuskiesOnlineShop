package tuskiesOnlineShop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static String DBNAME;
	private static String USERNAME;
	private static String PASSWORD;
	
	public DBConnection(String dbname, String username, String password) {
		DBNAME = dbname;
		USERNAME = username;
		PASSWORD = password;
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost/" + DBNAME + "?useSSL=false", USERNAME, PASSWORD);
	}
}
