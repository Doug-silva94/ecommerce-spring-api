package com.dev.api.springrest.exception;

import java.util.function.Supplier;

public class CategoryException extends Exception implements Supplier<CategoryException> {

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


    @Override
    public CategoryException get() {
        return (CategoryException) getCause();
    }
}
