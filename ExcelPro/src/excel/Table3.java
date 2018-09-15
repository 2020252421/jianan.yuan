package excel;

public class Table3 {
	private String title3;
	private String[] headers3;
	private Point3[] points3;
	public String getTitle3() {
		return title3;
	}
	public void setTitle3(String title3) {
		this.title3 = title3;
	}
	public String[] getHeaders3() {
		return headers3;
	}
	public void setHeaders3(String[] headers3) {
		this.headers3 = headers3;
	}
	public Point3[] getPoints3() {
		return points3;
	}
	public void setPoints3(Point3[] points3) {
		this.points3 = points3;
	}
	public Table3() {
		super();
	}
	public Table3(String title3, String[] headers3, Point3[] points3) {
		super();
		this.title3 = title3;
		this.headers3 = headers3;
		this.points3 = points3;
	}
	
}
