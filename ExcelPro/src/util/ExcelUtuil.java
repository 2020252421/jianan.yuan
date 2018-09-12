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
import excel.Point;
import excel.Table;

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
	public static void refreshSheet(String path,String fileName) {
		File xlsFile = new File(path+fileName);
		HSSFWorkbook workbook = readExcel(xlsFile);
		HSSFSheet sheet = workbook.getSheet("output");
		if(sheet!=null) {
			workbook.removeSheetAt(workbook.getSheetIndex(sheet.getSheetName()));
		}
		sheet= workbook.createSheet("output");
		genrateCover(sheet);
		Table table = new Table();
		table.setTitle("一、裂缝水平位移监测成果表");
		String[] headers = {"编号","本次位移（mm/d)","累计偏移（mm）","备注"};
		table.setHeaders(headers);
		Point[] points = new Point[16];
		for (int i = 1; i < 17; i++) {
			points[i-1] = getPoint(i, workbook, "裂缝水平");
		}
		table.setPoints(points);
		generateTable(sheet, table);
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
		HSSFFont font4 = sheet.getWorkbook().createFont();
		font4.setFontHeightInPoints((short)24);
		font4.setFontName("宋体");
		cellStyle.setFont(font4);
		Cover cover = new Cover();
		cover.setTitle("舟山新城金鸡山单元LC-09-02-10(B)地块住宅项目基坑开挖监测日报");
		cover.setVersion("第（ 2 ）期");
		cover.setOwner("核工业湖州工程勘察院");
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
		tableHeaderFont.setFontName("宋体");
		tableHeaderFont.setFontHeightInPoints((short)14);
		tableHeaderStyle.setFont(tableHeaderFont);
		tableHeaderStyle.setAlignment(HorizontalAlignment.CENTER);
		tableHeaderStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		tableHeader.setCellStyle(tableHeaderStyle);
		tableHeader.setCellValue(table.getTitle());
		
		HSSFCellStyle tableContentStyle = sheet.getWorkbook().createCellStyle();
		HSSFFont tableContentFont = sheet.getWorkbook().createFont();
		tableContentFont.setFontName("宋体");
		tableContentFont.setFontHeightInPoints((short)11);
		tableContentStyle.setFont(tableContentFont);
		tableContentStyle.setWrapText(true);
		tableContentStyle.setBorderBottom(BorderStyle.THIN);
		tableContentStyle.setBorderLeft(BorderStyle.THIN);
		tableContentStyle.setBorderTop(BorderStyle.THIN);
		tableContentStyle.setBorderRight(BorderStyle.THIN);
		HSSFCell  cell =null;
		int startRow = 52;
		int currentRow = startRow;
		HSSFRow[] tableRows = new HSSFRow[table.getPoints().length/2+1];
		HSSFCell[][] pointCells = new HSSFCell[table.getPoints().length][4];
		for (int i = 0; i <= table.getPoints().length/2; i++) {
			tableRows[i] = sheet.createRow(currentRow);
			tableRows[i].setHeight((short)(34*20));
			if(i==0) {
				for (int j = 0; j < 8; j++) {
					cell=tableRows[i].createCell(j);
					cell.setCellStyle(tableContentStyle);
					if(j<4) {
					cell.setCellValue(table.getHeaders()[j]);;
				}else {
					cell.setCellValue(table.getHeaders()[j-4]);
				}
			}
			}else {
				for (int j = 0; j < 4; j++) {
					tableRows[i].createCell(j);
					tableRows[i].getCell(j).setCellStyle(tableContentStyle);
					pointCells[i][j] = tableRows[i].getCell(j);
					tableRows[i].createCell(j+4);
					tableRows[i].getCell(j+4).setCellStyle(tableContentStyle);
					pointCells[i+pointCells.length/2-1][j] = tableRows[i].getCell(j+4);
				}
			}
			currentRow++;
		}
		for (int i = 0; i < table.getPoints().length; i++) {
			if(pointCells[i][0]!=null) {
				pointCells[i][0].setCellValue(table.getPoints()[i].getName());
				pointCells[i][1].setCellValue(table.getPoints()[i].getDatas()[2].getValue());
				pointCells[i][2].setCellValue(table.getPoints()[i].getDatas()[4].getValue());
				pointCells[i][3].setCellValue(table.getPoints()[i].getComments());
			}
		}
		return sheet;
	}
}
