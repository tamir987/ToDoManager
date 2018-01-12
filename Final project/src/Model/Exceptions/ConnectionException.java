package Model.Exceptions;

/**
 * Represents connection Exception thrown by the Data Access Layer.
 */
@SuppressWarnings("serial")
public class ConnectionException extends RuntimeException {

	public ConnectionException() {
	}

	public ConnectionException(String message) {
		super(message);
	}

	public ConnectionException(Throwable cause) {
		super(cause);
	}

	public ConnectionException(String message, Throwable cause) {
		super(message, cause);
	}
}
