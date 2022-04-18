package luxik.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ValueDate { //model for virtual table, that contains one value and one date
	@Id
	private float tempf;
	@Column
	private String date_ins;
	
	public ValueDate() {
	}//empty constructor
	
	public ValueDate(float tmp, String date) {
		this.setDate_ins(date);
		this.setValue(tmp);
	}//end constructor

	public String getDate_ins() {
		return date_ins;
	}

	public void setDate_ins(String date_ins) {
		this.date_ins = date_ins;
	}

	public float getValue() {
		return tempf;
	}

	public void setValue(float temp) {
		this.tempf = temp;
	}
	
}//end class
