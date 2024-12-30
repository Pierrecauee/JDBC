package model;

public class ModelException extends Exception {
	private static final long serialVersionUID = 1L;

	public ModelException(String message, Exception cause) {
		super(message, cause);
	}
}
