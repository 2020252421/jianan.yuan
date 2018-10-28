package com.jianan.entity;

public class Content3 {
	private String title3;
	private String conclusion3;
	private Table3[] tables3;
	public String getTitle3() {
		return title3;
	}
	public void setTitle3(String title3) {
		this.title3 = title3;
	}
	public String getConclusion3() {
		return conclusion3;
	}
	public void setConclusion3(String conclusion3) {
		this.conclusion3 = conclusion3;
	}
	public Table3[] getTables3() {
		return tables3;
	}
	public void setTables3(Table3[] tables3) {
		this.tables3 = tables3;
	}
	public Content3() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Content3(String title3, String conclusion3, Table3[] tables3) {
		super();
		this.title3 = title3;
		this.conclusion3 = conclusion3;
		this.tables3 = tables3;
	}
	
}
