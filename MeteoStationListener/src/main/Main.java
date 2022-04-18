package main;

import java.io.IOException;

import configuration.ConfigurationLoader;
import logging.LogWriter;
import tcp.TCPListener;
/**
 * 
 * @author luxik
 *
 */
public class Main {

	public static void main(String[] args) {
		/*String data = "GET /weatherstation/updateweatherstation.php?ID=Pokus&PASSWORD=Zkouska&action=updateraww&realtime=1&rtfreq=5&dateutc=now&baromin=29.84&tempf=43.7&dewptf=32.3&humidity=64&windspeedmph=2.0&windgustmph=2.0&winddir=55&rainin=0.0&dailyrainin=0.0&solarradiation=48.58&UV=0.0&indoortempf=71.7&indoorhumidity=52 HTTP/1.1";
		System.out.println(DatabaseStringParser.parseInsertString(ConfigurationLoader.getDatabaseTableName(), data));*/
		/*String[] splitted = data.split("&");
		HashMap<String, Float> dataToInsert = new HashMap();
		for (int i = 6; i < splitted.length; i++) {
			String [] split = splitted[i].split("=| ");
			dataToInsert.put(split[0], Float.parseFloat(split[1]));
		}
		*/
		try {
			ConfigurationLoader.LoadConfig();
			TCPListener tcp = new TCPListener(ConfigurationLoader.getServerPort(), ConfigurationLoader.getServerAddress());
			Thread thr = new Thread(tcp);
			thr.start();
			System.out.println("Meteo Listener v1.2 Started Succesfully");
		}//end try
		catch(IOException e) {
			LogWriter.WriteToLog(e);
		}//end catch
		catch(Exception e) {
			LogWriter.WriteToLog(e);
		}//end catch
	}//end method
}//end class
