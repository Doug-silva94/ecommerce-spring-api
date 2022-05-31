package com.dev.api.springrest.exceptions;

public class ProductSaleException extends Exception {

	private static final long serialVersionUID = 1L;

	public ProductSaleException() {
        super();
    }

    public ProductSaleException(String message) {
        super(message);
    }

    public ProductSaleException(String message, Exception cause) {
        super(message, cause);
    }

    public ProductSaleException(Exception e) {
        super(e);
    }
	
}
