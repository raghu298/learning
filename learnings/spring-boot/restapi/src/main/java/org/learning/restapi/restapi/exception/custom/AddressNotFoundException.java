package org.learning.restapi.restapi.exception.custom;

public class AddressNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private final String  message;

    public AddressNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
