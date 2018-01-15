package Model.Classes;

import Model.Exceptions.ValidationException;

/**
 * Represents a Validator class. This class have static methods, that validates
 * inputs by REGEX.
 */
public class Validator {
	/**
	 * Check if a specific string matches the pattern sent with it. If it does,
	 * it return true. otherwise, it return false.
	 * 
	 * @param checkValue
	 *            A string containing the username needs to be authenticated.
	 * @param pattern
	 *            A string containing the password needs to be authenticated..
	 * @return A boolean object that determine if the string sent to this method
	 *         matches the pattern sent with it.
	 */
	private static Boolean checkIfMatch(String checkValue, String pattern) {
		if ((checkValue.toString().matches(pattern)))
			return true;
		return false;
	}

	/**
	 * Checks if a string matches the pattern of a NAME. if it doesn't match -
	 * it throws a ValidationException.
	 * 
	 * @param name
	 *            A string representing a name.
	 * @throws ValidationException.
	 */
	public static void checkName(String name) throws ValidationException {
		// Pattern of a NAME.
		String pattern = "([a-zA-Z\\s]+)";
		if (!checkIfMatch(name, pattern)) {
			throw new ValidationException("illegal action. Name must contain only letters and spaces!");
		}
	}

	/**
	 * Checks if a string matches the pattern of a USERNAME. if it doesn't match
	 * - it throws a ValidationException.
	 * 
	 * @param userName
	 *            A string representing a username.
	 * @throws ValidationException.
	 */
	public static void checkUserName(String userName) throws ValidationException {
		// Pattern of a USERNAME.
		String pattern = "([a-zA-Z0-9]+)";
		if (!checkIfMatch(userName, pattern)) {
			throw new ValidationException("illegal action. Username must contain only letters and digits!");
		}
	}

	/**
	 * Checks if a string matches the pattern of a PASSWORD. if it doesn't match
	 * - it throws a ValidationException.
	 * 
	 * @param passWord
	 *            A string representing a password.
	 * @throws ValidationException.
	 */
	public static void checkPass(String passWord) throws ValidationException {
		// Pattern of a PASSWORD.
		String pattern = "[\\w]{2,8}";
		if (!checkIfMatch(passWord, pattern)) {
			throw new ValidationException("illegal action. Password must contain 3-8 digits or letters!");
		}
	}

	/**
	 * Checks if a string representing a password equals to a string
	 * representing it's confirmation. if it doesn't - it throws a
	 * ValidationException.
	 * 
	 * @param password
	 *            A string representing a password.
	 * @param confirmPassword
	 *            A string representing password's confirmation.
	 * @throws ValidationException.
	 */
	public static void checkPassAndConfirmation(String password, String confirmPassword) throws ValidationException {
		if (!(password.equals(confirmPassword))) {
			throw new ValidationException("illegal action. Password and it's confirmation are not the same!");
		}
	}

	/**
	 * Checks if a string matches the pattern of only letters, digits and
	 * spaces. if it doesn't match - it throws a ValidationException.
	 * 
	 * @param checkMe
	 *            A string to check with this specific pattern.
	 * @param field
	 *            A string representing the field we are checking. uses mainly
	 *            for the ValidationException message.
	 * @throws ValidationException.
	 */
	public static void checkField(String checkMe, String field) throws ValidationException {
		/*
		 * Pattern of a string containing only letters, digits and spaces - like
		 * location or mission.
		 */
		String pattern = "(\\w([\\w\\s]*))";
		if (!checkIfMatch(checkMe, pattern)) {
			throw new ValidationException(
					"illegal action. " + field + " must contain only letters, digits and spaces!");
		}
	}

	/**
	 * Checks if a string matches the pattern of a DATE. if it doesn't match -
	 * it throws a ValidationException.
	 * 
	 * @param date
	 *            A string representing a date.
	 * @throws ValidationException.
	 */
	public static void checkDate(String date) throws ValidationException {
		// Pattern of a DATE.
		String pattern = "((31(\\/|-|\\.)(0?[13578]|1[02]))|((29|30)(\\/|-|\\.)(0?[1,3-9]|1[0-2]))|(0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)((0?[1-9])|(1[0-2])))(\\/|-|\\.)(19[0-9]{2}|2[0-9]{3})";
		if (!checkIfMatch(date, pattern)) {
			throw new ValidationException("illegal action. date must be at the form of dd-mm-yyyy!");
		}
	}
}
