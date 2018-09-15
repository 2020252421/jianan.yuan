package excel;

public class Content2 {
	private String title2;
	private String conclusion2;
	private Table2[] tables2;	
	public String getTitle2() {
		return title2;
	}
	public void setTitle2(String title2) {
		this.title2 = title2;
	}
	public String getConclusion2() {
		return conclusion2;
	}
	public void setConclusion2(String conclusion2) {
		this.conclusion2 = conclusion2;
	}
	public Table2[] getTables2() {
		return tables2;
	}
	public void setTables2(Table2[] tables2) {
		this.tables2 = tables2;
	}
	public Content2() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Content2(String title2, String conclusion2, Table2[] tables2) {
		super();
		this.title2 = title2;
		this.conclusion2 = conclusion2;
		this.tables2 = tables2;
	}
	
}
