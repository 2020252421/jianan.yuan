package util;

public class FormatUtil {
	public static final String OUTPUTSHEETNAME = "output";
	public static final String SHEETNAME = "裂缝水平";
	public static final String TABLETITLE = "一、裂缝水平位移监测成果表";
	public static final String SHEETNAME2 = "全站仪水平";
	public static final String TABLETITLE2 = "二、维护墙顶水平位移监测成果表";
	public static final String SHEETNAME3 = "高程";
	public static final String TABLETITLE3 = "三、维护墙顶竖向位移监测成果表";
	public static final String FONTNAME = "宋体";
	public static final String COVERTITLE = "舟山新城金鸡山单元LC-09-02-10(B)地块住宅项目基坑开挖监测日报";
	public static final String COVERVERSION = "第（ 2 ）期";
	public static final String COVEROWNER = "核工业湖州工程勘察院";
	public static final String HEADER1 = "编号";
	public static final String HEADER2= "本次位移（mm/d)";
	public static final String HEADER3 = "累计偏移（mm）";
	public static final String HEADER4 = "备注";
	public static final String HEADER5 = "本次隆沉(mm/d)";
	public static final String HEADER6 = "累计隆沉（mm）";
	public static String getLineBreak(int number) {
		String breakers = "";
		for (int i = 0; i < number; i++) {
			breakers  = breakers +"\r\n";
		}
		return breakers;
	}
	public static int getRowNumByPoint(int points) {
		return (points % 2 == 0 ? points /2 :points/2+1);
	}
	public static int getRowNumByPoint2(int points2) {
		return (points2 % 2 ==0 ? points2 /2 :points2/2+1);
	}
}
