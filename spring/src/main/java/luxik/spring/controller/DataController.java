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

import luxik.spring.model.ValueDate;
import luxik.spring.service.IDataService;
import luxik.spring.convertions.UnitConvertor;

@RestController
public class DataController { //mapping controller for the app
	
	@Autowired
	private IDataService dataService;
	
	@CrossOrigin(origins="*") //allow any request origins
	@GetMapping(path="/api/newest/{value}") //get http mapping
	public @ResponseBody Float getNewestDataValue(@PathVariable String value) { //gets newest data for specified value
		switch(value) {
		case "tempf":
			return dataService.getNewestTempOut();
		case "tempc":
			return UnitConvertor.convertToCelsius(dataService.getNewestTempOut());
		case "indoortempf":
			return dataService.getNewestTempIn();
		case "indoortempc":
			return UnitConvertor.convertToCelsius(dataService.getNewestTempIn());
		}//end switch
		return null;
	}//end method
	
	@CrossOrigin(origins="*") //allow any request origins
	@GetMapping(path="/api/inhour/{value}/{day}") //get http mapping
	public @ResponseBody Iterable <Float> distinctValueInHour(@PathVariable String value,
			@PathVariable String day){ //gets all different values for specified value in specified hour
		List<Float> flt = new ArrayList<Float>();
		switch(value) {
		case "tempc":
			for(int hour = 0; hour < 24; hour++) {
				flt.add(UnitConvertor.convertToCelsius(dataService.getAverageInHour(hour, day, "tempf")));
			}//end for
			return flt;
		case "indoortempc":
			for(int hour = 0; hour < 24; hour++) {
				flt.add(UnitConvertor.convertToCelsius(dataService.getAverageInHour(hour, day, "indoortempf")));
			}//end for
			return flt;
		case "baromcm":
			for(int hour = 0; hour < 24; hour++) {
				flt.add(UnitConvertor.convertToCentimetres(dataService.getAverageInHour(hour, day, "baromin")));
			}//end for
			return flt;
		case "raincm":
			for(int hour = 0; hour < 24; hour++) {
				flt.add(UnitConvertor.convertToCentimetres(dataService.getAverageInHour(hour, day, "rainin")));
			}//end for
			return flt;
		case "windspeedkph":
			for(int hour = 0; hour < 24; hour++) {
				flt.add(UnitConvertor.convertToKPH(dataService.getAverageInHour(hour, day, "windspeedmph")));
			}//end for
			return flt;
		default:
			for(int hour =0; hour < 24; hour++) {
				flt.add(dataService.getAverageInHour(hour, day, value));
			}//end if
			return flt;
		}//end switch
	}//end method
	
	@CrossOrigin(origins="*") //allow any request origins
	@GetMapping(path="/api/weekly/{value}/{date}") //get http mapping
	public @ResponseBody Iterable<Float> distinctWeekly(@PathVariable String value, @PathVariable String date){
		List<Float> flt = new ArrayList<Float>();
		//gets all different values of a specified value for each day in a specified week
		switch(value) {
		case "tempc":
			for(int day = 0; day < 7; day++) {
				flt.add(UnitConvertor.convertToCelsius(dataService.getAverageInWeek(date, day, "tempf")));
			}//end for
			return flt;
		case "indoortempc":
			for(int day = 0; day < 7; day++) {
				flt.add(UnitConvertor.convertToCelsius(dataService.getAverageInWeek(date, day, "indoortempf")));
			}//end for
			return flt;
		case "baromcm":
			for(int day = 0; day < 7; day++) {
				flt.add(UnitConvertor.convertToCentimetres(dataService.getAverageInWeek(date, day, "baromin")));
			}//end for
			return flt;
		case "raincm": 
			for(int day = 0; day < 7; day++) {
				flt.add(UnitConvertor.convertToCentimetres(dataService.getAverageInWeek(date, day, "rainin")));
			}//end for
			return flt;
		case "windspeedkph":
			for(int day = 0; day < 7; day++) {
				flt.add(UnitConvertor.convertToKPH(dataService.getAverageInWeek(date, day, "windspeedmph")));
			}//end for
			return flt;
		default:
			for(int day = 0; day < 7; day++) {
				flt.add(dataService.getAverageInWeek(date, day, value));
			}//end for
			return flt;
		}//end switch
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
		List<Float> finalList = new ArrayList<Float>();
		List<Float> temporaryList;
		switch(value) {
		case "tempc":
			temporaryList = dataService.getAverageInMonth("tempf", month, year);
			for(Float flt : temporaryList) {
				finalList.add(UnitConvertor.convertToCelsius(flt));
			}//end for
			return finalList;
		case "indoortempc":
			temporaryList = dataService.getAverageInMonth("indoortempf", month, year);
			for(Float flt : temporaryList) {
				finalList.add(UnitConvertor.convertToCelsius(flt));
			}//end for
			return finalList;
		case "baromcm":
			temporaryList = dataService.getAverageInMonth("baromin", month, year);
			for(Float flt : temporaryList) {
				finalList.add(UnitConvertor.convertToCentimetres(flt));
			}//end for
			return finalList;
		case "raincm":
			temporaryList = dataService.getAverageInMonth("rainin", month, year);
			for(Float flt : temporaryList) {
				finalList.add(UnitConvertor.convertToCentimetres(flt));
			}//end for
			return finalList;
		case "windspeedkph":
			temporaryList = dataService.getAverageInMonth("rainin", month, year);
			for(Float flt : temporaryList) {
				finalList.add(UnitConvertor.convertToKPH(flt));
			}//end for
			return finalList;
		default:
			return dataService.getAverageInMonth(value, month, year);
		}//end switch
	}//end method
	
	@CrossOrigin(origins="*") //allow any request origins
	@GetMapping(path="/api/yearly/{value}/{year}") //get http mapping
	public @ResponseBody Iterable<Float> getInYear(@PathVariable String value, @PathVariable String year){
		List<Float> finalList = new ArrayList<Float>();
		List<Float> temporaryList;
		switch(value) {
		case "tempc":
			//gets all different values for specified value in each month in specified year
			temporaryList = dataService.getAverageInYear(year, "tempf");
			for(Float flt : temporaryList) {
				finalList.add(UnitConvertor.convertToCelsius(flt));
			}//end for
			return finalList;
		case "indoortempc":
			temporaryList = dataService.getAverageInYear(year, "indoortempf");
			for(Float flt : temporaryList) {
				finalList.add(UnitConvertor.convertToCelsius(flt));
			}//end for
			return finalList;
		case "baromcm":
			temporaryList = dataService.getAverageInYear(year, "baromin");
			for(Float flt : temporaryList) {
				finalList.add(UnitConvertor.convertToCentimetres(flt));
			}//end for
			return finalList;
		case "raincm":
			temporaryList = dataService.getAverageInYear(year, "rainin");
			for(Float flt : temporaryList) {
				finalList.add(UnitConvertor.convertToCentimetres(flt));
			}//end for
			return finalList;
		case "windspeedkph":
			temporaryList = dataService.getAverageInYear(year, "windspeedmph");
			for(Float flt : temporaryList) {
				finalList.add(UnitConvertor.convertToKPH(flt));
			}//end for
			return finalList;
		default:
			return dataService.getAverageInYear(year, value);
		}//end switch
	}//end method
	
	@CrossOrigin(origins="*") //allow any request origins
	@GetMapping(path="/api/max/{value}") //get http mapping
	public @ResponseBody HashMap<Float, String> getMax(@PathVariable String value){
		//gets maximal value for specified value
		HashMap<Float, String> tempMap;
		ValueDate tmp;
		switch(value) {
		case "tempf":
			tmp = dataService.getMaxTempOut();
			tempMap = new HashMap<Float, String>();
			tempMap.put(tmp.getValue(), tmp.getDate_ins());
			return tempMap;
		case "tempc":
			tmp = dataService.getMaxTempOut();
			tempMap = new HashMap<Float, String>();
			tempMap.put(UnitConvertor.convertToCelsius(tmp.getValue()), tmp.getDate_ins());
			return tempMap;
		case "indoortempf":
			tmp = dataService.getMaxTempIn();
			tempMap = new HashMap<Float, String>();
			tempMap.put(tmp.getValue(), tmp.getDate_ins());
			return tempMap;
		case "indoortempc":
			tmp = dataService.getMaxTempIn();
			tempMap = new HashMap<Float, String>();
			tempMap.put(UnitConvertor.convertToCelsius(tmp.getValue()), tmp.getDate_ins());
			return tempMap;
		case "rainin":
			tmp = dataService.getMaxRain();
			tempMap = new HashMap<Float, String>();
			tempMap.put(tmp.getValue(), tmp.getDate_ins());
			return tempMap;
		case "raincm":
			tmp = dataService.getMaxRain();
			tempMap = new HashMap<Float, String>();
			tempMap.put(UnitConvertor.convertToCentimetres(tmp.getValue()), tmp.getDate_ins());
			return tempMap;
		case "windspeedmph":
			tmp = dataService.getMaxWind();
			tempMap = new HashMap<Float, String>();
			tempMap.put(tmp.getValue(), tmp.getDate_ins());
			return tempMap;
		case "windspeedkph":
			tmp = dataService.getMaxWind();
			tempMap = new HashMap<Float, String>();
			tempMap.put(UnitConvertor.convertToKPH(tmp.getValue()), tmp.getDate_ins());
			return tempMap;
		}//end switch
		return null;
	}//end method
	
	@CrossOrigin(origins="*") //allow any request origins
	@GetMapping(path="/api/min/{value}") //get http mapping
	public @ResponseBody HashMap<Float, String> getMin(@PathVariable String value){
		//gets minimal value for specified value
		ValueDate temp;
		HashMap<Float, String> tempMap;
		switch(value) {
		case "tempf":
			temp = dataService.getMinTempOut();
			tempMap = new HashMap<Float, String>();
			tempMap.put(temp.getValue(), temp.getDate_ins());
			return tempMap;
		case "tempc":
			temp = dataService.getMinTempOut();
			tempMap = new HashMap<Float, String>();
			tempMap.put(UnitConvertor.convertToCelsius(temp.getValue()), temp.getDate_ins());
			return tempMap;
		case "indoortempf":
			temp = dataService.getMinTempIn();
			tempMap = new HashMap<Float, String>();
			tempMap.put(temp.getValue(), temp.getDate_ins());
			return tempMap;
		case "indoortempc":
			temp = dataService.getMinTempIn();
			tempMap = new HashMap<Float, String>();
			tempMap.put(UnitConvertor.convertToCelsius(temp.getValue()), temp.getDate_ins());
			return tempMap;
		}//end switch
		return null;
	}//end method
}//end class
