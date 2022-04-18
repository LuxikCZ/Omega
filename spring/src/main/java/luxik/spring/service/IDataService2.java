package luxik.spring.service;

import java.util.List;

import luxik.spring.model.ValueDate;

public interface IDataService2 {
	public List<Float> getDataInYear(String year, String value);
	public Float getNewestTempIn();
	public Float getNewestTempOut();
	public ValueDate getMaxTempOut();
	public ValueDate getMaxTempIn();
	public ValueDate getMaxRain();
	public ValueDate getMaxWind();
	public ValueDate getMinTempIn();
	public ValueDate getMinTempOut();
}//end service
