package luxik.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import luxik.spring.model.Data;
import luxik.spring.model.ValueDate;
import luxik.spring.repository.DataRepository;

@Service
public class DataService2 implements IDataService2 {
	
	@Autowired
	private DataRepository repository;

	@Override
	public List<Float> getDataInYear(String year, String value) {
		int yr = Integer.parseInt(year);
		List<Float> finalList = new ArrayList<Float>();
		
		for(int month = 1; month < 13; month ++) {
			List<Data> dataList = repository.getDataInYear(month, yr);
			List<Float> temporaryList = new ArrayList<Float>();
			for(Data data : dataList) {
				switch(value) {
				case "baromin":
					temporaryList.add(data.getBaromin());
					break;
				case "tempf":
					temporaryList.add(data.getTempf());
					break;
				case "dewptf":
					temporaryList.add(data.getDewptf());
					break;
				case "humidity":
					temporaryList.add(data.getHumidity());
					break;
				case "windspeedmph":
					temporaryList.add(data.getWindspeedmph());
					break;
				case "windgustmph":
					temporaryList.add(data.getWindgustmph());
					break;
				case "winddir":
					temporaryList.add(data.getWinddir());
					break;
				case "rainin":
					temporaryList.add(data.getWinddir());
					break;
				case "dailyrainin":
					temporaryList.add(data.getDailyrainin());
					break;
				case "solarradiation":
					temporaryList.add(data.getSolarradiation());
					break;
				case "UV":
					temporaryList.add(data.getUv());
					break;
				case "indoortempf":
					temporaryList.add(data.getIndoortempf());
					break;
				case "indoorhumidity":
					temporaryList.add(data.getIndoorhumidity());
					break;
				}//end switch
			}//end for
			float average = 0;
			for(Float flt : temporaryList) {
				average += flt;
			}//end for
			average /= temporaryList.size();
			finalList.add(average);
		}//end for
		
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
