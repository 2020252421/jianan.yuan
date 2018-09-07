package excel;

import java.util.Date;

public class MeasuredValue {
	private String value;
	private String type;
	private Date date;
	private String unit;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public MeasuredValue() {
		super();
	}
	public MeasuredValue(String value, String type, Date date, String unit) {
		super();
		this.value = value;
		this.type = type;
		this.date = date;
		this.unit = unit;
	}
	
}
