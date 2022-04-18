package luxik.spring.logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 * 
 * @author luxik
 *
 */
public class LogWriter {
	public static void WriteToLog(Object toLog) {
		File logFile = new File("springLog.log"); //log file
		if(!logFile.exists()) { //check if file exists
			try {
				logFile.createNewFile();
			}//end try
			catch(Exception e) {
				System.err.println("Error creating log file");
				return;
			}//end catch
		}//end if
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(logFile, true))){
			String write = LogStringParser.parseLogString(toLog);
			bw.write(write);
		}//end try
		catch (IOException e) {
			System.err.println("Error writing to log file");
		}//end catch
	}//end method
}//end class
