package org.learning.restapi.restapi.exception.custom;

public class RecordNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final String  message;

    public RecordNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
