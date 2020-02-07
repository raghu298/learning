package org.learning.restapi.restapi.exception.custom;

public class ValidationException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	private final String  message;

    public ValidationException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
