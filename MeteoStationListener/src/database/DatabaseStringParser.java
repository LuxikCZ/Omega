package database;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
/**
 * 
 * @author luxik
 *
 */
public class DatabaseStringParser {
	
	public static String parseInsertString(String tableName, String data) {
		HashMap<String, Float> dataToInsert = new HashMap<String, Float>();
		
		String [] splitted = data.split("&");
		//System.out.println(data);
		
		for (int i = 6; i < splitted.length; i++) {
			String [] split = splitted[i].split("=| ");
			dataToInsert.put(split[0], Float.parseFloat(split[1]));
		}//end for
		
		LocalDateTime date = LocalDateTime.now(); //datetime now
	    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); //MySQL datetime format
	    String formattedDate = dateFormatter.format(date);
	    
	    String keys = "";
	    String values = "";
	    Object [] keySet = dataToInsert.keySet().toArray();
	    for(int i = 0; i < keySet.length; i++) {
	    	int pomu = keySet.length - 1;
	    	if (i != pomu) {
	    		keys += "" + keySet[i] + ",";
	    		values += dataToInsert.get(keySet[i]) + ",";
	    	}//end if
	    	else {
	    		keys += "" + keySet[i] + "";
	    		values += dataToInsert.get(keySet[i]) + "";
	    	}//end else
	    }//end for
	    
	    String insertString = "INSERT INTO " + tableName + "(date_ins," + keys + ") VALUES ('" + formattedDate + "'," + values + ");";
	    //System.out.println(insertString);
		return insertString;
	}//end method

}//end class
