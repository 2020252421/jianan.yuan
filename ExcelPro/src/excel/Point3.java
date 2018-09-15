package excel;

public class Point3 {
	private String name3;
	private MeasuredValue3[] datas3;
	private String comments3;
	public String getName3() {
		return name3;
	}
	public void setName3(String name3) {
		this.name3 = name3;
	}
	public MeasuredValue3[] getDatas3() {
		return datas3;
	}
	public void setDatas3(MeasuredValue3[] datas3) {
		this.datas3 = datas3;
	}
	public String getComments3() {
		return comments3;
	}
	public void setComments3(String comments3) {
		this.comments3 = comments3;
	}
	public Point3() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Point3(String name3, MeasuredValue3[] datas3, String comments3) {
		super();
		this.name3 = name3;
		this.datas3 = datas3;
		this.comments3 = comments3;
	}
	public String toString() {
		String values3 = "datas2:";
		for (int i = 0; i < getDatas3().length; i++) {
			values3 = values3+getDatas3()[i].getValue3()+" / ";
		}
		return "name:" + getName3() + values3+" comments:" + getComments3();
	}
		public String getFillValue(int i) {
			String fillValue="";
			switch (i) {
			case 0:
				fillValue = getName3();
				break;
			case 1:
				fillValue = getDatas3()[5].getValue3();
				break;
			case 2:
				fillValue = getDatas3()[7].getValue3();
				break;
			case 3:
				fillValue = getComments3();
			default:
				break;
			}
			return fillValue;
	}
}
