package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import excel.Cover;
import excel.MeasuredValue;
import excel.MeasuredValue2;
import excel.Point;
import excel.Point2;
import excel.Table;
import excel.Table2;

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
			System.out.println("文件找不到");
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			System.out.println("finally");
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
		} catch (IOException e) {
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
		System.out.println(point.toString());
		return point;
	}
	public static Point2 getPoint2(int pointNum,HSSFWorkbook workbook,String sheetName ) {
		HSSFSheet sheet = workbook.getSheet(sheetName);
		HSSFRow row = sheet.getRow(pointNum);
		Point2 point2 = new Point2();
		MeasuredValue2[] datas2 = new MeasuredValue2[6];
		for (int i = 0; i < 6; i++) {
			MeasuredValue2 measuredValue2 = new MeasuredValue2();
			measuredValue2.setValue2(row.getCell(i+1).toString());
			datas2[i] = measuredValue2;
		}
		point2.setName2(row.getCell(4).toString());
		point2.setDatas2(datas2);
		point2.setComments2(row.getCell(7).toString());
		System.out.println(point2.toString());
		return point2;
	}
	public static void refreshSheet(String path,String fileName) {
		File xlsFile = new File(path+fileName);
		HSSFWorkbook workbook = readExcel(xlsFile);
		HSSFSheet sheet = workbook.getSheet(FormatUtil.OUTPUTSHEETNAME);
		if(sheet!=null) {
			workbook.removeSheetAt(workbook.getSheetIndex(sheet.getSheetName()));
		}
		sheet= workbook.createSheet(FormatUtil.OUTPUTSHEETNAME);
		genrateCover(sheet);
		Table table = new Table();
		table.setTitle(FormatUtil.TABLETITLE);
		String[] headers = {FormatUtil.HEADER1,FormatUtil.HEADER2,FormatUtil.HEADER3,FormatUtil.HEADER4};
		table.setHeaders(headers);
		Point[] points = new Point[16];
			for (int i = 1; i < 17; i++) {
			points[i-1] = getPoint(i, workbook, FormatUtil.SHEETNAME);
		}
			table.setPoints(points);
			
			
	    Table2 table2 = new Table2();
	    table2.setTitle2(FormatUtil.TABLETITLE2);
		String[] headers2 = {FormatUtil.HEADER1,FormatUtil.HEADER2,FormatUtil.HEADER3,FormatUtil.HEADER4};
		table2.setHeaders2(headers2);
		Point2[] points2 = new Point2[6];
			for (int i = 11; i < 17; i++) {
			points2[i-1] = getPoint2(i, workbook, FormatUtil.SHEETNAME2);
		}
			table2.setPoints2(points2);
		generateTable(sheet, table);
		generateTable2(sheet, table2);
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
		HSSFFont font1 = sheet.getWorkbook().createFont();//创建HSSFFont对象
		font1.setFontHeightInPoints((short)20);//设置字体大小
		font1.setFontName(FormatUtil.FONTNAME);//设置字体格式
		font1.setBold(true);//设置粗体
		cellStyle.setFont(font1);//将字体设置进去
		cellStyle.setWrapText(true);//设置自动换行
		coverCell.setCellStyle(cellStyle);//将单元格设置进去
		HSSFFont font2 = sheet.getWorkbook().createFont();
		font2.setFontHeightInPoints((short)18);
		font2.setFontName(FormatUtil.FONTNAME);
		cellStyle.setFont(font2);
		HSSFFont font3 = sheet.getWorkbook().createFont();
		font3.setFontHeightInPoints((short)20);
		font3.setFontName(FormatUtil.FONTNAME);
		cellStyle.setFont(font3);
		HSSFFont font4 = sheet.getWorkbook().createFont();
		font4.setFontHeightInPoints((short)24);
		font4.setFontName(FormatUtil.FONTNAME);
		cellStyle.setFont(font4);
		Cover cover = new Cover();
		cover.setTitle(FormatUtil.COVERTITLE);
		cover.setVersion(FormatUtil.COVERVERSION);
		cover.setOwner(FormatUtil.COVEROWNER);
		cover.setDate(new Date());
		String coverContent = "";
		coverContent = cover.getTitle()+FormatUtil.getLineBreak(8)+cover.getVersion()
		+FormatUtil.getLineBreak(8)+cover.getOwner()+FormatUtil.getLineBreak(1)
		+cover.getDate()+FormatUtil.getLineBreak(5);
		HSSFRichTextString ts = new HSSFRichTextString(coverContent);
		int start=0,end = cover.getTitle().length();
		ts.applyFont(start, end, font1);
		
		start = end;
		end = end+8;
		ts.applyFont(start, end, font4);
		
		start = end;
		end = end+cover.getVersion().length();
		ts.applyFont(start, end, font2);
		
		start = end;
		end = end+8;
		ts.applyFont(start, end, font4);
		
		start = end;
		end = end+cover.getOwner().length()+1+cover.getDate().toString().length();
		ts.applyFont(start, end, font3);
		
		coverCell.setCellValue(ts);
		coverCell.setCellStyle(cellStyle);
		return sheet;
	}
	public static HSSFSheet generateTable(HSSFSheet sheet,Table table) {
		sheet.addMergedRegion(new CellRangeAddress(51, 51, 0, 7));
		HSSFRow row = sheet.createRow(51);
		row.setHeight((short)(34*20));
		HSSFCell tableHeader = row.createCell(0);
		HSSFCellStyle tableHeaderStyle = sheet.getWorkbook().createCellStyle();
		HSSFFont tableHeaderFont = sheet.getWorkbook().createFont();
		tableHeaderFont.setBold(true);
		tableHeaderFont.setFontName(FormatUtil.FONTNAME);
		tableHeaderFont.setFontHeightInPoints((short)14);
		tableHeaderStyle.setFont(tableHeaderFont);
		tableHeaderStyle.setAlignment(HorizontalAlignment.CENTER);
		tableHeaderStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		tableHeader.setCellStyle(tableHeaderStyle);
		tableHeader.setCellValue(table.getTitle());
		
		HSSFCellStyle tableContentStyle = sheet.getWorkbook().createCellStyle();
		HSSFFont tableContentFont = sheet.getWorkbook().createFont();
		tableContentFont.setFontName(FormatUtil.FONTNAME);
		tableContentFont.setFontHeightInPoints((short)11);
		tableContentStyle.setFont(tableContentFont);
		tableContentStyle.setWrapText(true);
		tableContentStyle.setBorderBottom(BorderStyle.THIN);
		tableContentStyle.setBorderLeft(BorderStyle.THIN);
		tableContentStyle.setBorderTop(BorderStyle.THIN);
		tableContentStyle.setBorderRight(BorderStyle.THIN);
		HSSFCell  cell =null;
		int startRow = 52;
		HSSFRow headRow = sheet.createRow(startRow);
		headRow.setHeight((short)(34*20));
		for (int i = 0; i < 8; i++) {
			cell=headRow.createCell(i);
			cell.setCellStyle(tableContentStyle);
			if(i<4) {
				cell.setCellValue(table.getHeaders()[i]);
			}else {
				cell.setCellValue(table.getHeaders()[i-4]);
			}
		}
		startRow++;
		HSSFCell temCell = null;
		for (int i = 0; i < table.getPoints().length; i++) {
			if(i<FormatUtil.getRowNumByPoint(table.getPoints().length)) {
				HSSFRow newRow = sheet.createRow(startRow+i);
				newRow.setHeight((short)(34*20));
				for (int c = 0; c < 4; c++) {
					temCell =newRow.createCell(c);
					temCell.setCellValue(table.getPoints()[i].getFillValue(c));
					temCell.setCellStyle(tableContentStyle);
				}
				
			}else {
				for (int c =4; c < 8; c++) {
					temCell=sheet.getRow(startRow+i-FormatUtil
							.getRowNumByPoint(table.getPoints().length))
							.createCell(c);
					temCell.setCellValue(table.getPoints()[i].getFillValue(c-4));
					temCell.setCellStyle(tableContentStyle);
				}
			}
				
		}
		return sheet;
	}
	public static HSSFSheet generateTable2(HSSFSheet sheet,Table2 table2) {
		sheet.addMergedRegion(new CellRangeAddress(68, 68, 0, 8));
		HSSFRow row = sheet.createRow(68);
		row.setHeight((short)(34*20));
		HSSFCell tableHeader = row.createCell(0);
		HSSFCellStyle tableHeaderStyle = sheet.getWorkbook().createCellStyle();
		HSSFFont tableHeaderFont = sheet.getWorkbook().createFont();
		tableHeaderFont.setBold(true);
		tableHeaderFont.setFontName(FormatUtil.FONTNAME);
		tableHeaderFont.setFontHeightInPoints((short)14);
		tableHeaderStyle.setFont(tableHeaderFont);
		tableHeaderStyle.setAlignment(HorizontalAlignment.CENTER);
		tableHeaderStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		tableHeader.setCellStyle(tableHeaderStyle);
		tableHeader.setCellValue(table2.getTitle2());
		
		
		HSSFCellStyle tableContentStyle = sheet.getWorkbook().createCellStyle();
		HSSFFont tableContentFont = sheet.getWorkbook().createFont();
		tableContentFont.setFontName(FormatUtil.FONTNAME);
		tableContentFont.setFontHeightInPoints((short)11);
		tableContentStyle.setFont(tableContentFont);
		tableContentStyle.setWrapText(true);
		tableContentStyle.setBorderBottom(BorderStyle.THIN);
		tableContentStyle.setBorderLeft(BorderStyle.THIN);
		tableContentStyle.setBorderTop(BorderStyle.THIN);
		tableContentStyle.setBorderRight(BorderStyle.THIN);
		HSSFCell  cell =null;
		int startRow = 69;
		HSSFRow headRow = sheet.createRow(startRow);
		headRow.setHeight((short)(34*20));
		for (int i = 0; i < 3; i++) {
			cell=headRow.createCell(i);
			cell.setCellStyle(tableContentStyle);
			if(i<3) {
				cell.setCellValue(table2.getHeaders2()[i]);
			}else {
				cell.setCellValue(table2.getHeaders2()[i-3]);
			}
		}
		startRow++;
		HSSFCell temCell = null;
		for (int i = 0; i < table2.getPoints2().length; i++) {
			if(i<FormatUtil.getRowNumByPoint(table2.getPoints2().length)) {
				HSSFRow newRow = sheet.createRow(startRow+i);
				newRow.setHeight((short)(34*20));
				for (int c = 0; c < 4; c++) {
					temCell =newRow.createCell(c);
					temCell.setCellValue(table2.getPoints2()[i].getFillValue(c));
					temCell.setCellStyle(tableContentStyle);
				}
				
			}else {
				for (int c =4; c < 8; c++) {
					temCell=sheet.getRow(startRow+i-FormatUtil
							.getRowNumByPoint2(table2.getPoints2().length))
							.createCell(c);
					temCell.setCellValue(table2.getPoints2()[i].getFillValue(c-4));
					temCell.setCellStyle(tableContentStyle);
				}
			}
				
		}
		return sheet;
	}
}
