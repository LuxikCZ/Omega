package luxik.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


/**
 * Model for the whole database table
 * @author luxik
 */
@Entity
public class Data { //model for database table
	@Id
	private String date_ins;
	@Column
	private float baromin;
	@Column
	private float tempf;
	@Column
	private float dewptf;
	@Column
	private float humidity;
	@Column
	private float windspeedmph;
	@Column
	private float windgustmph;
	@Column
	private float winddir;
	@Column
	private float rainin;
	@Column
	private float dailyrainin;
	@Column
	private float solarradiation;
	@Column
	private float UV;
	@Column
	private float indoortempf;
	@Column
	private float indoorhumidity;
	
	public Data() {
	}//end empty constructor
	
	public Data(String date_ins, float baromin, float tempf, float dewptf, float humidity,
			float windspeedmph,float windgustmph, float winddir, float rainin, float dailyrainin,
			float solarradiation, float UV, float indoortempf, float indoorhumidity) {
		this.setDate_ins(date_ins);
		this.setBaromin(baromin);
		this.setTempf(tempf);
		this.setDewptf(dewptf);
		this.setHumidity(humidity);
		this.setWindspeedmph(windspeedmph);
		this.setWindgustmph(windgustmph);
		this.setWinddir(winddir);
		this.setRainin(rainin);
		this.setDailyrainin(dailyrainin);
		this.setSolarradiation(solarradiation);
		this.setUv(UV);
		this.setIndoortempf(indoortempf);
		this.setIndoorhumidity(indoorhumidity);
	}//end constructor

	//GETTERS AND SETTERS
	public String getDate_ins() {
		return date_ins;
	}

	public void setDate_ins(String date_ins) {
		this.date_ins = date_ins;
	}

	public float getBaromin() {
		return baromin;
	}

	public void setBaromin(float baromin) {
		this.baromin = baromin;
	}

	public float getTempf() {
		return tempf;
	}

	public void setTempf(float tempf) {
		this.tempf = tempf;
	}

	public float getDewptf() {
		return dewptf;
	}

	public void setDewptf(float dewptf) {
		this.dewptf = dewptf;
	}

	public float getHumidity() {
		return humidity;
	}

	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}

	public float getWindspeedmph() {
		return windspeedmph;
	}

	public void setWindspeedmph(float windspeedmph) {
		this.windspeedmph = windspeedmph;
	}

	public float getWindgustmph() {
		return windgustmph;
	}

	public void setWindgustmph(float windgustmph) {
		this.windgustmph = windgustmph;
	}

	public float getWinddir() {
		return winddir;
	}

	public void setWinddir(float winddir) {
		this.winddir = winddir;
	}

	public float getRainin() {
		return rainin;
	}

	public void setRainin(float rainin) {
		this.rainin = rainin;
	}

	public float getDailyrainin() {
		return dailyrainin;
	}

	public void setDailyrainin(float dailyrainin) {
		this.dailyrainin = dailyrainin;
	}

	public float getSolarradiation() {
		return solarradiation;
	}

	public void setSolarradiation(float solarradiation) {
		this.solarradiation = solarradiation;
	}

	public float getUv() {
		return UV;
	}

	public void setUv(float uv) {
		this.UV = uv;
	}

	public float getIndoortempf() {
		return indoortempf;
	}

	public void setIndoortempf(float indoortempf) {
		this.indoortempf = indoortempf;
	}

	public float getIndoorhumidity() {
		return indoorhumidity;
	}

	public void setIndoorhumidity(float indoorhumidity) {
		this.indoorhumidity = indoorhumidity;
	}
	
	public String toString() {
		final StringBuilder strb = new StringBuilder("Data{");
		strb.append("date_ins=").append(date_ins);
		strb.append(", baromin=").append(baromin);
		strb.append(", tempf=").append(tempf);
		strb.append(", humidity=").append(humidity);
		strb.append(", windspeedmph=").append(windspeedmph);
		strb.append(", windgustmph=").append(windgustmph);
		strb.append(", winddir=").append(winddir);
		strb.append(", rainin=").append(rainin);
		strb.append(", dailyrainin=").append(dailyrainin);
		strb.append(", UV=").append(UV);
		strb.append(", indoortempf=").append(indoortempf);
		strb.append("indoorhumidity").append(indoorhumidity);
		strb.append("}");
		
		return strb.toString();
	}//end toString
	
}//end class
