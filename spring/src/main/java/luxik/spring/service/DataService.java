package luxik.spring.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import luxik.spring.model.ValueDate;
import luxik.spring.repository.DataRepository;

@Service
public class DataService implements IDataService {

	@Autowired
	private DataRepository repository;

	@Override
	public Float getAverageInHour(Integer hour, String day, String value) {
		switch(value) {
		case "baromin":
			return repository.getAverageBaromInHour(hour, day);
		case "tempf":
			return repository.getAverageTempInHour(hour, day);
		case "dewptf":
			return repository.getAverageDewptInHour(hour, day);
		case "humidity":
			return repository.getAverageHumidityInHour(hour, day);
		case "windspeedmph":
			return repository.getAverageWindspeedInHour(hour, day);
		case "windgustmph":
			return repository.getAverageWindgustInHour(hour, day);
		case "winddir":
			return repository.getAverageWinddirInHour(hour, day);
		case "rainin":
			return repository.getAverageRainInHour(hour, day);
		case "dailyrainin":
			return repository.getAverageDailyrainInHour(hour, day);
		case "solarradiation":
			return repository.getAverageSolarradInHour(hour, day);
		case "UV":
			return repository.getAverageUVInHour(hour, day);
		case "indoortempf":
			return repository.getAverageIndoortempInHour(hour, day);
		case "indoorhumidity":
			return repository.getAverageIndoorhumInHour(hour, day);
		default:
			return null;
		}//end switch
	}//end method

	@Override
	public Float getAverageInWeek(String date, Integer day, String value) {
		switch(value) {
		case "baromin":
			return repository.getAverageBaromInWeek(date, day);
		case "tempf":
			return repository.getAverageTempInWeek(date, day);
		case "dewptf":
			return repository.getAverageDewptInWeek(date, day);
		case "humidity":
			return repository.getAverageHumidityInWeek(date, day);
		case "windspeedmph":
			return repository.getAverageWindspeedInWeek(date, day);
		case "windgustmph":
			return repository.getAverageWindgustInWeek(date, day);
		case "winddir":
			return repository.getAverageWinddirInWeek(date, day);
		case "rainin":
			return repository.getAverageRainInWeek(date, day);
		case "dailyrainin":
			return repository.getAverageDailyrainInWeek(date, day);
		case "solarradiation":
			return repository.getAverageSolarradInWeek(date, day);
		case "UV":
			return repository.getAverageUVInWeek(date, day);
		case "indoortempf":
			return repository.getAverageIndoortempInWeek(date, day);
		case "indoorhumidity":
			return repository.getAverageIndoorhumInWeek(date, day);
		default:
			return null;
		}//end switch
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
	public List<Float> getAverageInMonth(String value, String month, String year) {
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
		
			switch(value) {
			case "baromin":
				for(int day = 0; day < iterate; day++)
					finalList.add(repository.getAverageBaromInMonth(mt, yr, day+1));
				return finalList;
			case "tempf":
				for(int day = 0; day < iterate; day++)
					finalList.add(repository.getAverageTempInMonth(mt, yr, day+1));
				return finalList;
			case "dewptf":
				for(int day = 0; day < iterate; day++)
					finalList.add(repository.getAverageDewptInMonth(mt, yr, day+1));
				return finalList;
			case "humidity":
				for(int day = 0; day < iterate; day++)
					finalList.add(repository.getAverageHumidityInMonth(mt, yr, day+1));
				return finalList;
			case "windspeedmph":
				for(int day = 0; day < iterate; day++)
					finalList.add(repository.getAverageWindspeedInMonth(mt, yr, day+1));
				return finalList;
			case "windgustmph":
				for(int day = 0; day < iterate; day++)
					finalList.add(repository.getAverageWindgustInMonth(mt, yr, day+1));
				return finalList;
			case "winddir":
				for(int day = 0; day < iterate; day++)
					finalList.add(repository.getAverageWinddirInMonth(mt, yr, day+1));
				return finalList;
			case "rainin":
				for(int day = 0; day < iterate; day++)
					finalList.add(repository.getAverageRainInMonth(mt, yr, day+1));
				return finalList;
			case "dailyrainin":
				for(int day = 0; day < iterate; day++)
					finalList.add(repository.getAverageDailyrainInMonth(mt, yr, day+1));
				return finalList;
			case "solarradiation":
				for(int day = 0; day < iterate; day++)
					finalList.add(repository.getAverageSolarradInMonth(mt, yr, day+1));
				return finalList;
			case "UV":
				for(int day = 0; day < iterate; day++)
					finalList.add(repository.getAverageUVInMonth(mt, yr, day+1));
				return finalList;
			case "indoortempf":
				for(int day = 0; day < iterate; day++)
					finalList.add(repository.getAverageIndoortempInMonth(mt, yr, day+1));
				return finalList;
			case "indoorhumidity":
				for(int day = 0; day < iterate; day++)
					finalList.add(repository.getAverageIndoorhumInMonth(mt, yr, day+1));
				return finalList;
			}//end switch
		return finalList;
	}//end method
	
	@Override
	public List<Float> getAverageInYear(String year, String value) {
		int yr = Integer.parseInt(year);
		List<Float> finalList = new ArrayList<Float>();
		
		switch(value) {
		case "baromin":
			for(int month = 1; month < 13; month ++)
				finalList.add(repository.getAverageBaromInYear(month, yr));
			return finalList;
		case "tempf":
			for(int month = 1; month < 13; month ++)
				finalList.add(repository.getAverageTempInYear(month, yr));
			return finalList;
		case "dewptf":
			for(int month = 1; month < 13; month ++)
				finalList.add(repository.getAverageDewptInYear(month, yr));
			return finalList;
		case "humidity":
			for(int month = 1; month < 13; month ++)
				finalList.add(repository.getAverageHumidityInYear(month, yr));
			return finalList;
		case "windspeedmph":
			for(int month = 1; month < 13; month ++)
				finalList.add(repository.getAverageWindspeedInYear(month, yr));
			return finalList;
		case "windgustmph":
			for(int month = 1; month < 13; month ++)
				finalList.add(repository.getAverageWindgustInYear(month, yr));
			return finalList;
		case "winddir":
			for(int month = 1; month < 13; month ++)
				finalList.add(repository.getAverageWinddirInYear(month, yr));
			return finalList;
		case "rainin":
			for(int month = 1; month < 13; month ++)
				finalList.add(repository.getAverageRainInYear(month, yr));
			return finalList;
		case "dailyrainin":
			for(int month = 1; month < 13; month ++)
				finalList.add(repository.getAverageDailyrainInYear(month, yr));
			return finalList;
		case "solarradiation":
			for(int month = 1; month < 13; month ++)
				finalList.add(repository.getAverageSolarradInYear(month, yr));
			return finalList;
		case "UV":
			for(int month = 1; month < 13; month ++)
				finalList.add(repository.getAverageUVInYear(month, yr));
			return finalList;
		case "indoortempf":
			for(int month = 1; month < 13; month ++)
				finalList.add(repository.getAverageIndoortempInYear(month, yr));
			return finalList;
		case "indoorhumidity":
			for(int month = 1; month < 13; month ++)
				finalList.add(repository.getAverageIndoorhumInYear(month, yr));
			return finalList;
		}//end switch
		return finalList;
	}//end method

	@Override
	public Float getNewestTempIn() {
		return repository.getNewestTempIn();
	}//end method

	@Override
	public Float getNewestTempOut() {
		return repository.getNewestTempOut();
	}//end method

	@Override
	public ValueDate getMaxTempOut() {
		float temp =  repository.getMaxTempOut();
		String date = repository.getDateByMaxTempOut();
		return new ValueDate(temp, date);
	}//end method

	@Override
	public ValueDate getMaxTempIn() {
		float temp = repository.getMaxTempIn();
		String date = repository.getDateByMaxTempIn();
		return new ValueDate(temp,date);
	}//end method

	@Override
	public ValueDate getMaxRain() {
		float rain = repository.getMaxRain();
		String date = repository.getDateByMaxRain();
		return new ValueDate(rain, date);
	}//end method

	@Override
	public ValueDate getMaxWind() {
		float wind = repository.getMaxWind();
		String date = repository.getDateByMaxWind();
		return new ValueDate(wind, date);
	}//end method

	@Override
	public ValueDate getMinTempIn() {
		float temp = repository.getMinTempIn();
		String date = repository.getDateByMinTempIn();
		return new ValueDate(temp, date);
	}//end method

	@Override
	public ValueDate getMinTempOut() {
		float temp = repository.getMinTempOut();
		String date = repository.getDateByMinTempOut();
		return new ValueDate(temp, date);
	}//end method
	
}//end class
