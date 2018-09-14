package excel;

public class Table {
	private String title;
	private String[] headers;
	private Point[] points;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String[] getHeaders() {
		return headers;
	}
	public void setHeaders(String[] headers) {
		this.headers = headers;
	}
	public Point[] getPoints() {
		return points;
	}
	public void setPoints(Point[] points) {
		this.points = points;
	}
	public Table() {
		super();
	}
	public Table(String title, String[] headers, Point[] points) {
		super();
		this.title = title;
		this.headers = headers;
		this.points = points;
	}

	
}
