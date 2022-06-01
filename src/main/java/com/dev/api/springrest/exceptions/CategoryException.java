package com.dev.api.springrest.exceptions;

public class CategoryException extends Exception {

	private static final long serialVersionUID = 1L;

	public CategoryException() {
        super();
    }

    public CategoryException(String message) {
        super(message);
    }

    public CategoryException(String message, Exception cause) {
        super(message, cause);
    }

    public CategoryException(Exception e) {
        super(e);
    }

}