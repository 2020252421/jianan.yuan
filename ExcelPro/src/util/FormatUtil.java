package util;

public class FormatUtil {
	public static final String OUTPUTSHEETNAME = "output";
	public static final String SHEETNAME = "�ѷ�ˮƽ";
	public static final String TABLETITLE = "һ���ѷ�ˮƽλ�Ƽ��ɹ���";
	public static final String SHEETNAME2 = "ȫվ��ˮƽ";
	public static final String TABLETITLE2 = "����ά��ǽ��ˮƽλ�Ƽ��ɹ���";
	public static final String SHEETNAME3 = "�߳�";
	public static final String TABLETITLE3 = "����ά��ǽ������λ�Ƽ��ɹ���";
	public static final String FONTNAME = "����";
	public static final String COVERTITLE = "��ɽ�³ǽ�ɽ��ԪLC-09-02-10(B)�ؿ�סլ��Ŀ���ӿ��ڼ���ձ�";
	public static final String COVERVERSION = "�ڣ� 2 ����";
	public static final String COVEROWNER = "�˹�ҵ���ݹ��̿���Ժ";
	public static final String HEADER1 = "���";
	public static final String HEADER2= "����λ�ƣ�mm/d)";
	public static final String HEADER3 = "�ۼ�ƫ�ƣ�mm��";
	public static final String HEADER4 = "��ע";
	public static final String HEADER5 = "����¡��(mm/d)";
	public static final String HEADER6 = "�ۼ�¡����mm��";
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
