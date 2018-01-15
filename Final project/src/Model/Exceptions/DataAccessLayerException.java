package Model.Exceptions;

/**
 * Represents Exception thrown by the Data Access Layer.
 * 
 * @author Tamir Schwartzberg (tamir5021@gmail.com).
 */
@SuppressWarnings("serial")
public class DataAccessLayerException extends Exception {

	public DataAccessLayerException() {
	}

	public DataAccessLayerException(String message) {
		super(message);
	}

	public DataAccessLayerException(Throwable cause) {
		super(cause);
	}

	public DataAccessLayerException(String message, Throwable cause) {
		super(message, cause);
	}
}
