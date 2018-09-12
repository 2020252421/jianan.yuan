package util;

public class FormatUtil {
	public static String getLineBreak(int number) {
		String breakers = "";
		for (int i = 0; i < number; i++) {
			breakers  = breakers +"\r\n";
		}
		return breakers;
	}
}
