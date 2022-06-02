package com.dev.api.springrest.exception;

public class ProductException extends Exception {

    private static final long serialVersionUID = 1L;

    public ProductException() {
        super();
    }

    public ProductException(String message) {
        super(message);
    }

    public ProductException(String message, Exception cause) {
        super(message, cause);
    }

    public ProductException(Exception e) {
        super(e);
    }

}
