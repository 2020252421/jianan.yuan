package com.jianan.entity;


public class Point {
	private String name;
	private MeasuredValue[] datas;
	private String comments;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public MeasuredValue[] getDatas() {
		return datas;
	}
	public void setDatas(MeasuredValue[] datas) {
		this.datas = datas;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Point() {
		super();
	}
	public Point(String name, MeasuredValue[] datas, String comments) {
		super();
		this.name = name;
		this.datas = datas;
		this.comments = comments;
	}
	@Override
	public String toString() {
		String values = "datas:";
		for (int i = 0; i < getDatas().length; i++) {
			values = values+getDatas()[i].getValue()+" / ";
		}
		return "name:" + getName() + values+" comments:" + getComments();
	}
	public String getFillValue(int i) {
		String fillValue="";
		switch (i) {
		case 0:
			fillValue = getName();
			break;
		case 1:
			fillValue = getDatas()[2].getValue();
			break;
		case 2:
			fillValue = getDatas()[4].getValue();
			break;
		case 3:
			fillValue = getComments();
		default:
			break;
		}
		return fillValue;
	}
	
}
