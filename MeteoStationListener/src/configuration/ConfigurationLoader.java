package configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import logging.LogWriter;
/***
 * 
 * @author luxik
 *
 */
public class ConfigurationLoader {
	private static String serverAddress /*= "127.0.0.1"*/;
	private static int serverPort/* = 65532*/;
	private static String databaseServerAddress/* = "127.0.0.1"*/;
	private static int databaseServerPort /*= 3306*/;
	private static String databaseName /*= "meteo"*/;
	private static String databaseUsername /*= "root"*/;
	private static String databasePassword /*= ""*/;
	private static String databaseTableName /*= "data"*/;
	
	public static void LoadConfig() { //method for loading configuration files
		Properties prop = new Properties();
		File configFile = new File("meteoListener.conf"); //location of server configuration file
		File databaseConfigFile = new File("meteoDatabase.conf"); //location of database configuration file
		
		try (FileInputStream fis = new FileInputStream(configFile)) { //load server configuration
			prop.load(fis);
			
			serverAddress = (String)prop.get("SERVER_ADDRESS");
			serverPort = Integer.parseInt((String)prop.get("SERVER_PORT"));
		}//end try
		catch (IOException e) {
			//e.printStackTrace();
			LogWriter.WriteToLog(e);
		}//end catch
		
		try (FileInputStream fis = new FileInputStream(databaseConfigFile)) { //load database configuration
			prop.load(fis);
			
			databaseServerAddress = (String)prop.get("DATABASE_SERVER");
			databaseServerPort = Integer.parseInt((String)prop.get("DATABASE_PORT"));
			databaseName = (String)prop.get("DATABASE_NAME");
			databaseUsername = (String)prop.get("DATABASE_USERNAME");
			databasePassword = (String)prop.get("DATABASE_PASSWORD");
			databaseTableName = (String)prop.get("DATABASE_TABLE");
		}//end try
		catch (IOException e){
			//e.printStackTrace();
			LogWriter.WriteToLog(e);
		}//end catch
	}//end method

	public static String getServerAddress() {
		return serverAddress;
	}

	public static int getServerPort() {
		return serverPort;
	}

	public static String getDatabaseServerAddress() {
		return databaseServerAddress;
	}

	public static int getDatabaseServerPort() {
		return databaseServerPort;
	}

	public static String getDatabaseName() {
		return databaseName;
	}

	public static String getDatabaseUsername() {
		return databaseUsername;
	}

	public static String getDatabasePassword() {
		return databasePassword;
	}
	
	public static String getDatabaseTableName() {
		return databaseTableName;
	}
	
}//end class
