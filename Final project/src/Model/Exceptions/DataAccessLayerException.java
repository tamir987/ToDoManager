package Model.Exceptions;

/**
 * Represents Exception thrown by the Data Access Layer.
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
