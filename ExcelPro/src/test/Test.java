package test;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import util.ExcelUtuil;

public class Test {
	public static void main(String[] args) {
		String path = "C://Users//ASUS//Desktop//excel//";
		String fileName = "513 .xls";
		HSSFWorkbook workbook = ExcelUtuil.readExcel(path, fileName);
		for (int i = 1; i < 17; i++) {
			ExcelUtuil.getPoint(i, workbook, "ÁÑ·ìË®Æ½");
		}
		ExcelUtuil.refreshSheet(path, fileName);
	}
}
