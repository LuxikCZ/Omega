package luxik.spring.convertions;

/**
 * Class for unit conversions
 * @author luxik
 */
public class UnitConvertor {

	/**
	 * Method for converting temperature from fahrenheit unit to celsius
	 * @param tempf Temperature in fahrenheit
	 * @return Temperature in fahrenheit, rounded to two decimals
	 */
	public static Float convertToCelsius(Float tempf) {
		if(tempf != null) {
			float tempc = (float) ((tempf-32)/1.8);
			tempc = (float)(Math.round(tempc*100.0)/100.0); //rounds value to two decimal numbers
			return tempc;
		}//end if
		return null;
	}//end method

	/**
	 * Method for converting from inches to centimetres
	 * @param inches value in inches
	 * @return value in centimetres rounded to two decimals
	 */
	public static Float convertToCentimetres(Float inches) {
		if(inches != null) {
			float centimetres = (float) (inches*2.54);
			centimetres = (float)(Math.round(centimetres*100.0)/100.0); //rounds value to two decimal numbers
			return centimetres;
		}//end if
		return null;
	}//end method

	/**
	 * Method for converting MPH to KPG
	 * @param mph value in Miles per hour
	 * @return value in Kilometres per hour
	 */
	public static Float convertToKPH(Float mph) {
		if(mph != null) {
			float kph = (float) (mph*1.609344);
			kph = (float)(Math.round(kph*100.0)/100.0); //rounds value to two decimal numbers
			return kph;
		}//end if
		return null;
	}//end method

}//end class
