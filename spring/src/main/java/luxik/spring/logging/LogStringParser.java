package luxik.spring.logging;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * 
 * @author luxik
 *
 */
public class LogStringParser {
	protected static String parseLogString(Object write) {
		String logString = "";
		
		LocalDateTime date = LocalDateTime.now(); //datetime now
	    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-dd-MM HH:mm:ss"); //MySQL datetime format
	    String formattedDate = dateFormatter.format(date);
	    
	    logString += formattedDate + " >>> ";
	    
	    if(write instanceof Exception) {
	    	Exception ex = (Exception) write;
	    	logString += "Exception:" + ex.getClass().toString().replace("class ", " ") + " occured. Cause: " + ex.getMessage();
	    }//end if
	    else {
	    	logString += write.toString();
	    }//end else
	    
		return logString + "\n";
	}//end method
}//end class
