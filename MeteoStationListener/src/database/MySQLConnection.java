package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import configuration.ConfigurationLoader;
import logging.LogWriter;
/**
 * 
 * @author luxik
 *
 */
public class MySQLConnection {
	private String url;
	private String username;
	private String password;
	
	public MySQLConnection(){
		url = "jdbc:mysql://" 
				+ ConfigurationLoader.getDatabaseServerAddress() 
				+ ":" + ConfigurationLoader.getDatabaseServerPort() 
				+ "/" + ConfigurationLoader.getDatabaseName()
				+ "?useSSL=false"; //MySQL connection string
		username = ConfigurationLoader.getDatabaseUsername(); //MySQL connection username
		password = ConfigurationLoader.getDatabasePassword(); //MySQL connection password
	}//end constructor
	
	public void insert(String insertString) { //method for inserting data into MySQL database
		try(Connection con = DriverManager.getConnection(url, username, password)){
			Statement statement = con.createStatement();
			statement.executeUpdate(insertString); //insert
		}//end try
		catch (SQLException e) {
			//e.printStackTrace();
			LogWriter.WriteToLog(e);
		}//end catch
	}//end method
}//end class
