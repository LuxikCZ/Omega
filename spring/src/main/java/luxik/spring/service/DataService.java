package luxik.spring.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import luxik.spring.model.Data;
import luxik.spring.repository.DataRepository;

@Service
public class DataService implements IDataService {

	@Autowired
	private DataRepository repository;
	
	public List<Data> getData(){ //gets ALL data from database
		return (List<Data>) repository.findAll();
	}//end method

	@Override
	public Data getNewestData() {
		List<Data> dat = repository.getNewestData();
		return dat.get(0);
	}//end method

	@Override
	public float getDistinctDataInHour(Integer hour, String day, String value) {
		List<Float> flt = new ArrayList<Float>();
		List<Data> dat = repository.getDistinctDataInHour(hour, day);
		
		for (Data data : dat) {
			switch(value) {
			case "baromin":
				flt.add(data.getBaromin());
				break;
			case "tempf":
				flt.add(data.getTempf());
				break;
			case "dewptf":
				flt.add(data.getDewptf());
				break;
			case "humidity":
				flt.add(data.getHumidity());
				break;
			case "windspeedmph":
				flt.add(data.getWindspeedmph());
				break;
			case "windgustmph":
				flt.add(data.getWindgustmph());
				break;
			case "winddir":
				flt.add(data.getWinddir());
				break;
			case "rainin":
				flt.add(data.getWinddir());
				break;
			case "dailyrainin":
				flt.add(data.getDailyrainin());
				break;
			case "solarradiation":
				flt.add(data.getSolarradiation());
				break;
			case "UV":
				flt.add(data.getUv());
				break;
			case "indoortempf":
				flt.add(data.getIndoortempf());
				break;
			case "indoorhumidity":
				flt.add(data.getIndoorhumidity());
				break;
			}//end switch
		}//end for
		float average = 0;
		for(float floa : flt) {
			average += floa;
		}//end for
		average = average/flt.size();
		return average;
	}//end method

	@Override
	public float getDistinctWeekly(String date, Integer day, String value) {
		List<Float> flt = new ArrayList<Float>();
		List<Data> dat = repository.getDistinctWeekly(date, day);
		
		for (Data data : dat) {
			switch(value) {
			case "baromin":
				flt.add(data.getBaromin());
				break;
			case "tempf":
				flt.add(data.getTempf());
				break;
			case "dewptf":
				flt.add(data.getDewptf());
				break;
			case "humidity":
				flt.add(data.getHumidity());
				break;
			case "windspeedmph":
				flt.add(data.getWindspeedmph());
				break;
			case "windgustmph":
				flt.add(data.getWindgustmph());
				break;
			case "winddir":
				flt.add(data.getWinddir());
				break;
			case "rainin":
				flt.add(data.getWinddir());
				break;
			case "dailyrainin":
				flt.add(data.getDailyrainin());
				break;
			case "solarradiation":
				flt.add(data.getSolarradiation());
				break;
			case "UV":
				flt.add(data.getUv());
				break;
			case "indoortempf":
				flt.add(data.getIndoortempf());
				break;
			case "indoorhumidity":
				flt.add(data.getIndoorhumidity());
				break;
			}//end switch
		}//end for
		
		float average = 0;
		for(float floa : flt) {
			average += floa;
		}//end for
		average = average/flt.size();
		return average;
	}//end method
	
	@Override
	public List<Integer> getYears() {
		List<Integer> itl = repository.getYears();
		return itl;
	}//end method

	@Override
	public HashMap<Integer, String> getMonths() {
		List<Integer> itl = repository.getMonths();
		HashMap<Integer, String> hmp = new HashMap<Integer, String>();
		for(Integer ite : itl) {
			switch(ite) {
			case 1:
				hmp.put(ite, "Leden");
				break;
			case 2:
				hmp.put(ite,"Únor");
				break;
			case 3:
				hmp.put(ite, "Březen");
				break;
			case 4:
				hmp.put(ite, "Duben");
				break;
			case 5:
				hmp.put(ite, "Květen");
				break;
			case 6:
				hmp.put(ite, "Červen");
				break;
			case 7:
				hmp.put(ite, "Červenec");
				break;
			case 8:
				hmp.put(ite, "Srpen");
				break;
			case 9:
				hmp.put(ite, "Září");
				break;
			case 10:
				hmp.put(ite, "Říjen");
				break;
			case 11:
				hmp.put(ite, "Listopad");
				break;
			case 12:
				hmp.put(ite, "Prosinec");
				break;
			}//end switch
		}//end for
		return hmp;
	}//end method

	@Override
	public List<Float> getInMonth(String value, String month, String year) {
		int iterate = 0;
		int yr = Integer.parseInt(year);
		int mt = Integer.parseInt(month);
		//month day count check
		if(mt == 1 || mt == 3 || mt == 5 || mt == 7 || mt == 8 || mt == 10 || mt == 12) {
			//checks if the month has 31 days
			iterate = 31;
		}//end if
		else if (mt == 2) { //February check
			if(yr%4 == 0) { //checks if the year is the transition year or not
				iterate = 29; //transition year => February has 29 days
			}//end if
			else {
				iterate = 28; //!transition year => February has 28 days
			}//end else
		}//end else if
		else {
			iterate = 30;
		}//end else
		
		List<Float> finalList = new ArrayList<Float>(); //list that will be returned
		for(int day = 0; day < iterate; day++) {
			List<Data> dataList = repository.getDataInMonth(mt, yr, day+1); //get data from database
			List<Float> floatList = new ArrayList<Float>(); //temporary list
			for(Data data : dataList) { //gets all data for each day
				switch(value) { //determines which value the user selected
				case "baromin":
					floatList.add(data.getBaromin());
					break;
				case "tempf":
					floatList.add(data.getTempf());
					break;
				case "dewptf":
					floatList.add(data.getDewptf());
					break;
				case "humidity":
					floatList.add(data.getHumidity());
					break;
				case "windspeedmph":
					floatList.add(data.getWindspeedmph());
					break;
				case "windgustmph":
					floatList.add(data.getWindgustmph());
					break;
				case "winddir":
					floatList.add(data.getWinddir());
					break;
				case "rainin":
					floatList.add(data.getWinddir());
					break;
				case "dailyrainin":
					floatList.add(data.getDailyrainin());
					break;
				case "solarradiation":
					floatList.add(data.getSolarradiation());
					break;
				case "UV":
					floatList.add(data.getUv());
					break;
				case "indoortempf":
					floatList.add(data.getIndoortempf());
					break;
				case "indoorhumidity":
					floatList.add(data.getIndoorhumidity());
					break;
				}//end switch
			}//end for
			float average = 0;
			for (float flt : floatList) {
				average += flt;
			}//end for
			average = average/floatList.size(); //counts the average value for each day
			finalList.add(average);
		}//end for
		
		return finalList;
	}//end method
}//end class
