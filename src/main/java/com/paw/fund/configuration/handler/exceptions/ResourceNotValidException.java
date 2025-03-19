package com.paw.fund.configuration.handler.exceptions;

import java.lang.RuntimeException;

public class ResourceNotValidException extends RuntimeException {
    public ResourceNotValidException(String message) {
        super(message);
    }

    public ResourceNotValidException() {
        super("Tài nguyên không hợp lệ");
    }
}
