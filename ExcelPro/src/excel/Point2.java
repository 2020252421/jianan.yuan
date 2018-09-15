package excel;

public class Point2 {
	private String name2;
	private MeasuredValue2[] datas2;
	private String comments2;
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public MeasuredValue2[] getDatas2() {
		return datas2;
	}
	public void setDatas2(MeasuredValue2[] datas2) {
		this.datas2 = datas2;
	}
	public String getComments2() {
		return comments2;
	}
	public void setComments2(String comments2) {
		this.comments2 = comments2;
	}
	public Point2() {
		super();
	}
	public Point2(String name2, MeasuredValue2[] datas2, String comments2) {
		super();
		this.name2 = name2;
		this.datas2 = datas2;
		this.comments2 = comments2;
	}
	public String toString() {
		String values2 = "datas2:";
		for (int i = 0; i < getDatas2().length; i++) {
			values2 = values2+getDatas2()[i].getValue2()+" / ";
		}
		return "name:" + getName2() + values2+" comments:" + getComments2();
	}
	public String getFillValue(int i) {
		String fillValue="";
		switch (i) {
		case 0:
			fillValue = getName2();
			break;
		case 1:
			fillValue = getDatas2()[4].getValue2();
			break;
		case 2:
			fillValue = getDatas2()[5].getValue2();
			break;
		case 3:
			fillValue = getComments2();
		default:
			break;
		}
		return fillValue;
	}
	
}
