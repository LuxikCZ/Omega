package luxik.spring.service;

import java.util.HashMap;
import java.util.List;

import luxik.spring.model.Data;

public interface IDataService
{
	public List<Data> getData();
	public Data getNewestData();
	public float getDistinctDataInHour(Integer hour, String day, String value);
	public float getDistinctWeekly(String date, Integer day, String value);
	public List<Integer> getYears();
	public HashMap<Integer, String> getMonths();
	public List<Float> getInMonth(String value, String month, String year);
}//end interface
