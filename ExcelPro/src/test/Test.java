package test;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import util.ExcelUtuil;
import util.FormatUtil;

public class Test {
	public static void main(String[] args) {
		String path = "C://Users//ASUS//Desktop//excel//";
		String fileName = "513 .xls";
		HSSFWorkbook workbook = ExcelUtuil.readExcel(path, fileName);
		for (int i = 1; i < 17; i++) {
			ExcelUtuil.getPoint(i, workbook, FormatUtil.SHEETNAME);
		}
		for (int i = 11; i < 17; i++) {
			ExcelUtuil.getPoint2(i, workbook, FormatUtil.SHEETNAME2);
		}
		ExcelUtuil.refreshSheet(path, fileName);
	}
}
