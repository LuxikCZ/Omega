package luxik.spring.convertions;

public class UnitConvertor {
	
	public static Float convertToCelsius(Float tempf) {
		if(tempf != null) {
			float tempc = (float) ((tempf-32)/1.8);
			tempc = (float)(Math.round(tempc*100.0)/100.0); //rounds value to two decimal numbers
			return tempc;
		}//end if
		return null;
	}//end method
	
	public static Float convertToCentimetres(Float inches) {
		if(inches != null) {
			float centimetres = (float) (inches*2.54);
			centimetres = (float)(Math.round(centimetres*100.0)/100.0); //rounds value to two decimal numbers
			return centimetres;
		}//end if
		return null;
	}//end method
	
	public static Float convertToKPH(Float mph) {
		if(mph != null) {
			float kph = (float) (mph*1.609344);
			kph = (float)(Math.round(kph*100.0)/100.0); //rounds value to two decimal numbers
			return kph;
		}//end if
		return null;
	}//end method

}//end class
