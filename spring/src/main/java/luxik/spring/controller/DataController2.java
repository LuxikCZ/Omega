package luxik.spring.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import luxik.spring.model.ValueDate;
import luxik.spring.service.IDataService2;

@RestController
public class DataController2 {

	@Autowired
	IDataService2 dataService2;
	
	@CrossOrigin(origins="*") //allow any request origins
	@GetMapping(path="/api/max/{value}") //get http mapping
	public @ResponseBody HashMap<Float, String> getMax(@PathVariable String value){
		//gets maximal value for specified value
		if(value.contentEquals("tempf")) {
			ValueDate tmp = dataService2.getMaxTempOut();
			HashMap<Float, String> tempMap = new HashMap<Float, String>();
			tempMap.put(tmp.getValue(), tmp.getDate_ins());
			return tempMap;
		}//end if
		else if(value.contentEquals("tempc")) {
			ValueDate tmp = dataService2.getMaxTempOut();
			HashMap<Float, String> tempMap = new HashMap<Float, String>();
			tempMap.put((float) ((tmp.getValue()-32)/1.8), tmp.getDate_ins());
			return tempMap;
		}//end if
		else if(value.contentEquals("indoortempf")) {
			ValueDate tmp = dataService2.getMaxTempIn();
			HashMap<Float, String> tempMap = new HashMap<Float, String>();
			tempMap.put(tmp.getValue(), tmp.getDate_ins());
			return tempMap;
		}//end else if
		else if(value.contentEquals("indoortempc")) {
			ValueDate tmp = dataService2.getMaxTempIn();
			HashMap<Float, String> tempMap = new HashMap<Float, String>();
			tempMap.put((float) ((tmp.getValue()-32)/1.8), tmp.getDate_ins());
			return tempMap;
		}//end else if
		else if(value.contentEquals("rainin")) {
			ValueDate val = dataService2.getMaxRain();
			HashMap<Float, String> rainMap = new HashMap<Float, String>();
			rainMap.put(val.getValue(), val.getDate_ins());
			return rainMap;
		}//end else if
		else if(value.contentEquals("raincm")) {
			ValueDate val = dataService2.getMaxRain();
			HashMap<Float, String> rainMap = new HashMap<Float, String>();
			rainMap.put((float) (val.getValue()*2.54), val.getDate_ins());
			return rainMap;
		}//end else if
		else if(value.contentEquals("windspeedmph")) {
			ValueDate val = dataService2.getMaxWind();
			HashMap<Float, String> windMap = new HashMap<Float, String>();
			windMap.put(val.getValue(), val.getDate_ins());
			return windMap;
		}//end else if
		else if(value.contentEquals("windspeedkph")) {
			ValueDate val = dataService2.getMaxWind();
			HashMap<Float, String> windMap = new HashMap<Float, String>();
			windMap.put((float) (val.getValue()*1.609344), val.getDate_ins());
			return windMap;
		}//end else if
		return null;
	}//end method
	
	@CrossOrigin(origins="*") //allow any request origins
	@GetMapping(path="/api/min/{value}") //get http mapping
	public @ResponseBody HashMap<Float, String> getMin(@PathVariable String value){
		//gets minimal value for specified value
		if(value.contentEquals("tempf")) {
			ValueDate temp = dataService2.getMinTempOut();
			HashMap<Float, String> tempMap = new HashMap<Float, String>();
			tempMap.put(temp.getValue(), temp.getDate_ins());
			return tempMap;
		}//end if
		else if(value.contentEquals("tempc")) {
			ValueDate temp = dataService2.getMinTempOut();
			HashMap<Float, String> tempMap = new HashMap<Float, String>();
			tempMap.put((float) ((temp.getValue()-32)/1.8), temp.getDate_ins());
			return tempMap;
		}//end else if
		else if(value.contentEquals("indoortempf")) {
			ValueDate temp = dataService2.getMinTempIn();
			HashMap<Float, String> tempMap = new HashMap<Float, String>();
			tempMap.put(temp.getValue(), temp.getDate_ins());
			return tempMap;
		}//end else if
		else if(value.contentEquals("indoortempc")) {
			ValueDate temp = dataService2.getMinTempIn();
			HashMap<Float, String> tempMap = new HashMap<Float, String>();
			tempMap.put((float) ((temp.getValue()-32)/1.8), temp.getDate_ins());
			return tempMap;
		}//end else if
		return null;
	}//end method
}//end class
