package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import excel.MeasuredValue;
import excel.Point;

public class ExcelUtuil {
	public static HSSFWorkbook readExcel(String path,String fileName) {
		File file = new File(path+fileName);
		HSSFWorkbook workbook = null;
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
			workbook = new HSSFWorkbook(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		if(workbook!=null) {
			System.out.println("∂¡»°excel≥…π¶£°");
		}
		return workbook;
	}
	public static Point getPoint(int pointNum,HSSFWorkbook workbook,String sheetName ) {
		HSSFSheet sheet = workbook.getSheet(sheetName);
		HSSFRow row = sheet.getRow(pointNum);
		Point point = new Point();
		MeasuredValue[] datas = new MeasuredValue[5];
		for (int i = 0; i < 5; i++) {
			MeasuredValue measuredValue = new MeasuredValue();
			measuredValue.setValue(row.getCell(i+1).toString());
			datas[i] = measuredValue;
		}
		point.setName(row.getCell(0).toString());
		point.setDatas(datas);
		point.setComments(row.getCell(6).toString());
		System.out.println(point);
		return point;
	}
}
