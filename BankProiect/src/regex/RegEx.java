package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx {

	static String emailPattern = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
	static String cnpPattern = "^[1-25-6]\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])(0[1-9]|[1-4]\\d|5[0-2]|99)(00[1-9]|0[1-9]\\d|[1-9]\\d\\d)\\d$";
	static String phonePattern = "^(\\+4|)?(07[0-8]{1}[0-9]{1}|02[0-9]{2}|03[0-9]{2}){1}?(\\s|\\.|\\-)?([0-9]{3}(\\s|\\.|\\-|)){2}$";
	static String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\\$%\\^&\\*])(?=.{8,})";

	public static boolean checkEmail(String email) {
		Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
		if (validate(email, pattern)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean checkCNP(String cnp) {
		Pattern pattern = Pattern.compile(cnpPattern, Pattern.CASE_INSENSITIVE);
		if (validate(cnp, pattern)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean checkPhone(String phone) {
		Pattern pattern = Pattern.compile(phonePattern, Pattern.CASE_INSENSITIVE);
		if (validate(phone, pattern)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean checkPassword(String pass) {
		Pattern pattern = Pattern.compile(passwordPattern, Pattern.CASE_INSENSITIVE);
		if (validate(pass, pattern)) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean validate(String string, Pattern pattern) {
		Matcher matcher = pattern.matcher(string);
		return matcher.find();
	}

}
