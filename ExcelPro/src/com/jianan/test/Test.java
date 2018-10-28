package com.jianan.test;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.jianan.util.ExcelUtuil;
import com.jianan.util.FormatUtil;

public class Test {
	public static void main(String[] args) {
		String path = "C://Users//ASUS//Desktop//excel//";
		String fileName = "513 .xls";
		HSSFWorkbook workbook = ExcelUtuil.readExcel(path, fileName);
		for (int i = 1; i < 17; i++) {
			ExcelUtuil.getPoint(i, workbook, FormatUtil.SHEETNAME);
		}
		for (int i = 1; i < 7; i++) {
			ExcelUtuil.getPoint2(i+10, workbook, FormatUtil.SHEETNAME2);
		}
		for (int i = 1; i < 7 ; i++) {
			ExcelUtuil.getPoint3(i+9, workbook, FormatUtil.SHEETNAME3);
		}
		ExcelUtuil.refreshSheet(path, fileName);
	}
}
