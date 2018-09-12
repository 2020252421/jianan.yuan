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
			System.out.println("�ļ��Ҳ���");
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			System.out.println("finally");
		}
		if(workbook!=null) {
			System.out.println("��ȡexcel�ɹ���");
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
			System.out.println("��ȡexcel�ɹ���");
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
		table.setTitle("һ���ѷ�ˮƽλ�Ƽ��ɹ���");
		String[] headers = {"���","����λ�ƣ�mm/d)","�ۼ�ƫ�ƣ�mm��","��ע"};
		table.setHeaders(headers);
		Point[] points = new Point[16];
		for (int i = 1; i < 17; i++) {
			points[i-1] = getPoint(i, workbook, "�ѷ�ˮƽ");
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
		//�ϲ���Ԫ��CellRangeAddress����������α�ʾ��ʼ�У������У���ʼ�У� ������
		HSSFRow row = sheet.createRow(0);//��HSSFrow����
		HSSFCell coverCell = row .createCell(0);//����HSSFCell����
		HSSFCellStyle cellStyle = sheet.getWorkbook().createCellStyle();//���õ�Ԫ����ʽ
		cellStyle.setAlignment(HorizontalAlignment.CENTER);//���þ��ж����ʽ
		HSSFFont font1 = sheet.getWorkbook().createFont();//����HSSFFont����
		font1.setFontHeightInPoints((short)20);//���������С
		font1.setFontName("����");//���������ʽ
		font1.setBold(true);//���ô���
		cellStyle.setFont(font1);//���������ý�ȥ
		cellStyle.setWrapText(true);//�����Զ�����
		coverCell.setCellStyle(cellStyle);//����Ԫ�����ý�ȥ
		HSSFFont font2 = sheet.getWorkbook().createFont();
		font2.setFontHeightInPoints((short)18);
		font2.setFontName("����");
		cellStyle.setFont(font2);
		HSSFFont font3 = sheet.getWorkbook().createFont();
		font3.setFontHeightInPoints((short)20);
		font3.setFontName("����");
		cellStyle.setFont(font3);
		HSSFFont font4 = sheet.getWorkbook().createFont();
		font4.setFontHeightInPoints((short)24);
		font4.setFontName("����");
		cellStyle.setFont(font4);
		Cover cover = new Cover();
		cover.setTitle("��ɽ�³ǽ�ɽ��ԪLC-09-02-10(B)�ؿ�סլ��Ŀ���ӿ��ڼ���ձ�");
		cover.setVersion("�ڣ� 2 ����");
		cover.setOwner("�˹�ҵ���ݹ��̿���Ժ");
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
		tableHeaderFont.setFontName("����");
		tableHeaderFont.setFontHeightInPoints((short)14);
		tableHeaderStyle.setFont(tableHeaderFont);
		tableHeaderStyle.setAlignment(HorizontalAlignment.CENTER);
		tableHeaderStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		tableHeader.setCellStyle(tableHeaderStyle);
		tableHeader.setCellValue(table.getTitle());
		return sheet;
	}
}
