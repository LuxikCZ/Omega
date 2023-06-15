package luxik.spring.service;

import java.util.HashMap;
import java.util.List;

import luxik.spring.model.ValueDate;

public interface IDataService
{
	public Float getAverageInHour(Integer hour, String day, String value);
	public Float getAverageInWeek(String date, Integer day, String value);
	public List<Integer> getYears();
	public HashMap<Integer, String> getMonths();
	public List<Float> getAverageInMonth(String value, String month, String year);
	public List<Float> getAverageInYear(String year, String value);
	public Float getNewestTempIn();
	public Float getNewestTempOut();
	public ValueDate getMaxTempOut();
	public ValueDate getMaxTempIn();
	public ValueDate getMaxRain();
	public ValueDate getMaxWind();
	public ValueDate getMinTempIn();
	public ValueDate getMinTempOut();
}//end interface
