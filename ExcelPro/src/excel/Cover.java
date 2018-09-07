package excel;

import java.util.Date;

public class Cover {
	private String title;
	private String version;
	private Date date;
	private String owner;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public Cover() {
		super();
	}
	public Cover(String title, String version, Date date, String owner) {
		super();
		this.title = title;
		this.version = version;
		this.date = date;
		this.owner = owner;
	}

}
