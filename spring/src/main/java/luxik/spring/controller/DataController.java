package luxik.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import luxik.spring.model.Data;
import luxik.spring.service.IDataService;
import luxik.spring.service.IDataService2;

@RestController
public class DataController { //mapping controller for the app
	
	@Autowired
	private IDataService dataService;
	@Autowired
	private IDataService2 dataService2;
	
	@CrossOrigin(origins="*") //allow any request origins
	@GetMapping(path="/api/data") //get http mapping
	public @ResponseBody Iterable <Data> listData() {
		return dataService.getData(); //gets all data from DB
	}//end method
	
	@CrossOrigin(origins="*") //allow any request origins
	@GetMapping(path="/api/newest") //get http mapping
	public @ResponseBody Data newestData() {
		return dataService.getNewestData(); //gets the newest data from DB
	}//end method
	
	@CrossOrigin(origins="*") //allow any request origins
	@GetMapping(path="/api/newest/{value}") //get http mapping
	public @ResponseBody Float getNewestDataValue(@PathVariable String value) { //gets newest data for specified value
		if(value.contentEquals("tempf")) { //gets temp in fahrenheit
			return dataService2.getNewestTempOut();
		}//end if
		else if(value.contentEquals("tempc")) { //gets temp in celsius
			return (float) ((dataService2.getNewestTempOut()-32)/1.8);
		}//end else if
		else if(value.contentEquals("indoortempf")) { //gets indoortemp in fahrenheit
			return dataService2.getNewestTempIn();
		}//end else if
		else if(value.contentEquals("indoortempc")) { //gets indoortemp in celsius
			return (float) ((dataService2.getNewestTempIn()-32)/1.8);
		}//end else if
		return null;
	}//end method
	
	@CrossOrigin(origins="*") //allow any request origins
	@GetMapping(path="/api/inhour/{value}/{day}") //get http mapping
	public @ResponseBody Iterable <Float> distinctValueInHour(@PathVariable String value,
			@PathVariable String day){ //gets all different values for specified value in specified hour
		List<Float> flt = new ArrayList<Float>();
		if(value.contentEquals("tempc")) {
			for(int hour = 0; hour < 24; hour++) {
				value = "tempf";
				flt.add((float) ((dataService.getDistinctDataInHour(hour, day, value)-32)/1.8));
			}//end for
		}//end if
		else if(value.contentEquals("indoortempc")) {
			for(int hour = 0; hour < 24; hour ++) {
				value = "indoortempf";
				flt.add((float) ((dataService.getDistinctDataInHour(hour, day, value)-32)/1.8));
			}//end for
		}//end else if
		else if(value.contentEquals("baromcm")) {
			for(int hour = 0; hour < 24; hour ++) {
				value = "baromin";
				flt.add((float) (dataService.getDistinctDataInHour(hour, day, value)*2.54));
			}//end for
		}//end else if
		else if(value.contentEquals("raincm")) {
			for(int hour = 0; hour < 24; hour ++) {
				value = "rainin";
				flt.add((float) (dataService.getDistinctDataInHour(hour, day, value)*2.54));
			}//end for
		}//end else if
		else if(value.contentEquals("windspeedkph")) {
			for(int hour = 0; hour < 24; hour ++) {
				value = "windspeedmph";
				flt.add((float) (dataService.getDistinctDataInHour(hour, day, value)*1.609344));
			}//end for
		}//end else if
		else {
			for(int hour =0; hour < 24; hour++) {
				flt.add(dataService.getDistinctDataInHour(hour, day, value));
			}//end if
		}//end else
		return flt;
	}//end method
	
	@CrossOrigin(origins="*") //allow any request origins
	@GetMapping(path="/api/weekly/{value}/{date}") //get http mapping
	public @ResponseBody Iterable<Float> distinctWeekly(@PathVariable String value, @PathVariable String date){
		List<Float> flt = new ArrayList<Float>();
		//gets all different values of a specified value for each day in a specified week
		if(value.contentEquals("tempc")) {
			for(int day = 0; day < 7; day++) {
				value = "tempf";
				flt.add((float) ((dataService.getDistinctWeekly(date, day, value)-32)/1.8));
			}//end for
		}//end if
		else if(value.contentEquals("indoortempc")) {
			for(int day = 0; day < 7; day++) {
				value = "indoortempf";
				flt.add((float) ((dataService.getDistinctWeekly(date, day, value)-32)/1.8));
			}//end for
		}//end else if
		else if(value.contentEquals("baromcm")) {
			for(int day = 0; day < 7; day++) {
				value = "baromin";
				flt.add((float) (dataService.getDistinctWeekly(date, day, value)*2.54));
			}//end for
		}//end else if
		else if(value.contentEquals("raincm")) {
			for(int day = 0; day < 7; day++) {
				value = "rainin";
				flt.add((float) (dataService.getDistinctWeekly(date, day, value)*2.54));
			}//end for
		}//end else if
		else if(value.contentEquals("windspeedkph")) {
			for(int day = 0; day < 7; day++) {
				value = "windspeedmph";
				flt.add((float) (dataService.getDistinctWeekly(date, day, value)*1.609344));
			}//end for
		}//end else if
		else {
			for(int day = 0; day < 7; day++) {
				flt.add(dataService.getDistinctWeekly(date, day, value));
			}//end for
		}//end else
		return flt;
	}//end method
	
	@CrossOrigin(origins="*") //allow any request origins
	@GetMapping(path="/api/years") //get http mapping
	public @ResponseBody Iterable<Integer> getYears(){ //gets all years that are mentioned in the DB
		return dataService.getYears();
	}//end method
	
	@CrossOrigin(origins="*") //allow any request origins
	@GetMapping(path="/api/months") //get http mapping
	public @ResponseBody HashMap<Integer, String> getMonths(){ //gets all months mentioned in the DB
		return dataService.getMonths();
	}//end method
	
	@CrossOrigin(origins="*") //allow any request origins
	@GetMapping(path="/api/monthly/{value}/{month}/{year}") //get http mapping
	public @ResponseBody Iterable<Float> getInMonth(@PathVariable String value, @PathVariable String month,
			@PathVariable String year){
		//gets all different values for specified value for each day in specified month
		if(value.contentEquals("tempc")) {
			List<Float> finalList = new ArrayList<Float>();
			List<Float> floatList = dataService.getInMonth("tempf", month, year);
			for(float flt : floatList) {
				finalList.add((float) ((flt-32)/1.8));
			}//end for
			return finalList;
		}//end if
		else if(value.contentEquals("indoortempc")) {
			List<Float> finalList = new ArrayList<Float>();
			List<Float> floatList = dataService.getInMonth("indoortempf", month, year);
			for(float flt : floatList) {
				finalList.add((float) ((flt-32)/1.8));
			}//end for
			return finalList;
		}//end else if
		else if(value.contentEquals("baromcm")) {
			List<Float> finalList = new ArrayList<Float>();
			List<Float> floatList = dataService.getInMonth("baromin", month, year);
			for(float flt : floatList) {
				finalList.add((float) (flt * 2.54));
			}//end for
			return finalList;
		}//end else if
		else if(value.contentEquals("raincm")) {
			List<Float> finalList = new ArrayList<Float>();
			List<Float> floatList = dataService.getInMonth("rainin", month, year);
			for(float flt : floatList) {
				finalList.add((float) (flt * 2.54));
			}//end for
			return finalList;
		}//end else if
		else if(value.contentEquals("windspeedkph")) {
			List<Float> finalList = new ArrayList<Float>();
			List<Float> floatList = dataService.getInMonth("rainin", month, year);
			for(float flt : floatList) {
				finalList.add((float) (flt * 1.609344));
			}//end for
			return finalList;
		}//end else if
		else {
			return dataService.getInMonth(value, month, year);
		}//end else
	}//end method
	
	@CrossOrigin(origins="*") //allow any request origins
	@GetMapping(path="/api/yearly/{value}/{year}") //get http mapping
	public @ResponseBody Iterable<Float> getInYear(@PathVariable String value, @PathVariable String year){
		if(value.contentEquals("tempc")) {
			//gets all different values for specified value in each month in specified year
			List<Float> temporaryList = dataService2.getDataInYear(year, "tempf");
			List<Float> finalList = new ArrayList<Float>();
			for(Float flt : temporaryList) {
				finalList.add((float) ((flt-32)/1.8));
			}//end for
			return finalList;
		}//end if
		else if(value.contentEquals("indoortempc")) {
			List<Float> temporaryList = dataService2.getDataInYear(year, "indoortempf");
			List<Float> finalList = new ArrayList<Float>();
			for(Float flt : temporaryList) {
				finalList.add((float) ((flt-32)/1.8));
			}//end for
			return finalList;
		}//end else if
		else if(value.contentEquals("baromcm")) {
			List<Float> temporaryList = dataService2.getDataInYear(year, "baromin");
			List<Float> finalList = new ArrayList<Float>();
			for(Float flt : temporaryList) {
				finalList.add((float) (flt*2.54));
			}//end for
			return finalList;
		}//end else if
		else if(value.contentEquals("raincm")) {
			List<Float> temporaryList = dataService2.getDataInYear(year, "rainin");
			List<Float> finalList = new ArrayList<Float>();
			for(Float flt : temporaryList) {
				finalList.add((float) (flt*2.54));
			}//end for
			return finalList;
		}//end else if
		else if(value.contentEquals("windspeedkph")) {
			List<Float> temporaryList = dataService2.getDataInYear(year, "windspeedmph");
			List<Float> finalList = new ArrayList<Float>();
			for(Float flt : temporaryList) {
				finalList.add((float) (flt*1.609344));
			}//end for
			return finalList;
		}//end else if
		else {
			return dataService2.getDataInYear(year, value);
		}//end else
	}//end method
}//end class
