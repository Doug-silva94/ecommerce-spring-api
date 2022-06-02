package com.dev.api.springrest.exception;

public class EmployeeException extends Exception {

    private static final long serialVersionUID = 1L;

    public EmployeeException() {
        super();
    }

    public EmployeeException(String message) {
        super(message);
    }

    public EmployeeException(String message, Exception cause) {
        super(message, cause);
    }

    public EmployeeException(Exception e) {
        super(e);
    }

}
