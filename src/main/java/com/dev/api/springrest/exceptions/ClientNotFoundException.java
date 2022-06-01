package com.dev.api.springrest.exceptions;

public class ClientNotFoundException extends ClientException {

    public ClientNotFoundException() {
        super();
    }

    public ClientNotFoundException(String message) {
        super(message);
    }

    public ClientNotFoundException(String message, Exception cause) {
        super(message, cause);
    }

    public ClientNotFoundException(Exception e) {
        super(e);
    }


}