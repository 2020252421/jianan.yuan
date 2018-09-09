package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

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
			System.out.println("读取excel成功！");
		}
		return workbook;
	}
	public static HSSFWorkbook readExcel(File xlsFile) {
		HSSFWorkbook workbook = null;
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(xlsFile);
			workbook = new HSSFWorkbook(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		if(workbook!=null) {
			System.out.println("读取excel成功！");
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
	public static void refreshSheet(String path,String fileName) {
		File xlsFile = new File(path+fileName);
		HSSFWorkbook workbook = readExcel(xlsFile);
		HSSFSheet sheet = workbook.getSheet("output");
		if(sheet!=null) {
			workbook.removeSheetAt(workbook.getSheetIndex(sheet.getSheetName()));
		}
		sheet= workbook.createSheet("output");
		genrateCover(sheet);
		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(xlsFile);
			workbook.write(outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(outputStream!=null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("output sheet has been reflashed!");
	}
	public static HSSFSheet genrateCover(HSSFSheet sheet) {
		sheet.addMergedRegion(new CellRangeAddress(0, 49, 0, 8));
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		HSSFRow row = sheet.createRow(0);//建HSSFrow对象
		HSSFCell coverCell = row .createCell(0);//创建HSSFCell对象
		HSSFCellStyle cellStyle = sheet.getWorkbook().createCellStyle();//设置单元格样式
		cellStyle.setAlignment(HorizontalAlignment.CENTER);//设置居中对齐格式
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		HSSFFont font1 = sheet.getWorkbook().createFont();//创建HSSFFont对象
		font1.setFontHeightInPoints((short)20);//设置字体大小
		font1.setFontName("宋体");//设置字体格式
		font1.setBold(true);//设置粗体
		cellStyle.setFont(font1);//将字体设置进去
		cellStyle.setWrapText(true);//设置自动换行
		coverCell.setCellStyle(cellStyle);//将单元格设置进去
		HSSFFont font2 = sheet.getWorkbook().createFont();
		font2.setFontHeightInPoints((short)18);
		font2.setFontName("宋体");
		cellStyle.setFont(font2);
		HSSFFont font3 = sheet.getWorkbook().createFont();
		font3.setFontHeightInPoints((short)20);
		font3.setFontName("宋体");
		cellStyle.setFont(font3);
		HSSFRichTextString tString = new HSSFRichTextString(
				"\n\n"+
				"舟山新城金鸡山单元LC-09-02-10(B)地块住宅项目基坑开挖监测日报"+"\n\n\n\n\n\n\n\n\n"+
				"第（ 2 ）期"+"\n\n\n\n\n\n\n\n\n\n\n\n"+"核工业湖州工程勘察院"+ 
				"\n"+"2018年04月17日"+"\n\n");
		tString.applyFont(0, 40, font1);
		tString.applyFont(41, 55, font2);
		tString.applyFont(60, 90, font3);
		coverCell.setCellValue(tString);
		coverCell.setCellStyle(cellStyle);
		return sheet;
	}
}
