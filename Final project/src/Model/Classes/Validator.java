package Model.Classes;

import Model.Exceptions.ValidationException;

public class Validator {

	private static Boolean checkIfMatch(String checkValue, String pattern) {
		if ((checkValue.toString().matches(pattern)))
			return true;
		return false;
	}

	public static void checkName(String name) throws ValidationException {
		String pattern = "([a-zA-Z\\s]+)";
		if (!checkIfMatch(name, pattern)) {
			throw new ValidationException("illegal action. Name must contain only letters and spaces!");
		}
	}

	public static void checkUserName(String userName) throws ValidationException {
		String pattern = "([a-zA-Z0-9]+)";
		if (!checkIfMatch(userName, pattern)) {
			throw new ValidationException("illegal action. Username must contain only letters and digits!");
		}
	}

	public static void checkPass(String passWord) throws ValidationException {
		String pattern = "[\\w]{2,8}";
		if (!checkIfMatch(passWord, pattern)) {
			throw new ValidationException("illegal action. Password must contain 3-8 digits or letters!");
		}
	}

	public static void checkPassAndConfirmation(String password, String confirmPassword) throws ValidationException {
		if (!(password.equals(confirmPassword))) {
			throw new ValidationException("illegal action. Password and it's confirmation are not the same!");
		}
	}

	public static void checkField(String checkMe, String field) throws ValidationException {
		String pattern = "(\\w([\\w\\s]*))";
		if (!checkIfMatch(checkMe, pattern)) {
			throw new ValidationException(
					"illegal action. " + field + " must contain only letters, digits and spaces!");
		}
	}

	public static void checkDate(String date) throws ValidationException {
		String pattern = "((31(\\/|-|\\.)(0?[13578]|1[02]))|((29|30)(\\/|-|\\.)(0?[1,3-9]|1[0-2]))|(0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)((0?[1-9])|(1[0-2])))(\\/|-|\\.)(19[0-9]{2}|2[0-9]{3})";
		if (!checkIfMatch(date, pattern)) {
			throw new ValidationException("illegal action. date must be at the form of dd-mm-yyyy!");
		}
	}
}
