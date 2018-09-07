package excel;

public class Content {
	private String title;
	private String conclusion;
	private Table[] tables;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getConclusion() {
		return conclusion;
	}
	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}
	public Table[] getTables() {
		return tables;
	}
	public void setTables(Table[] tables) {
		this.tables = tables;
	}
	public Content() {
		super();
	}
	public Content(String title, String conclusion, Table[] tables) {
		super();
		this.title = title;
		this.conclusion = conclusion;
		this.tables = tables;
	}
	
}
