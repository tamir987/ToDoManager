package Model.Exceptions;

/**
 * Represents validation Exception thrown by the Model layer.
 * 
 * @author Tamir Schwartzberg (tamir5021@gmail.com).
 */
@SuppressWarnings("serial")
public class ValidationException extends Exception {

	public ValidationException() {
	}

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(Throwable cause) {
		super(cause);
	}

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}
}