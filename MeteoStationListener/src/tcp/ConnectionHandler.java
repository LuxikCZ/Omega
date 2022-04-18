package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import configuration.ConfigurationLoader;
import database.DatabaseStringParser;
import database.MySQLConnection;
import logging.LogWriter;
/**
 * 
 * @author luxik
 *
 */
public class ConnectionHandler implements Runnable {
	private Socket clientSocket;
	private BufferedReader input;
	
	protected ConnectionHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}//end constructor
	
	/*
	 * GET /weatherstation/updateweatherstation.php?ID=Pokus&PASSWORD=Zkouska&action=updateraww&realtime=1&rtfreq=5&dateutc=now&
	 * baromin=29.84&
	 * tempf=43.7&
	 * dewptf=32.3&
	 * humidity=64&
	 * windspeedmph=2.0&
	 * windgustmph=2.0&
	 * winddir=55&
	 * rainin=0.0&
	 * dailyrainin=0.0&
	 * solarradiation=48.58&
	 * UV=0.0&
	 * indoortempf=71.7&
	 * indoorhumidity=52 HTTP/1.1
	 */
	
	@Override
	public void run() {
		try {
			input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String data = input.readLine(); //receives data from client
			clientSocket.close();//after receiving data from client, connection is closed
			
			String insertString = DatabaseStringParser.parseInsertString(ConfigurationLoader.getDatabaseTableName(), data);
			
			MySQLConnection sql = new MySQLConnection();
			sql.insert(insertString);
		}//end try
		catch (IOException e) {
			//e.printStackTrace();
			LogWriter.WriteToLog(e);
		}//end catch
		catch (Exception e) {
			//e.printStackTrace();
			LogWriter.WriteToLog(e);
		}//end catch
	}//end method
}//end class
