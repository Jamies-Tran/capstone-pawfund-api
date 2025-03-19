package com.paw.fund.configuration.handler.exceptions;

import java.lang.RuntimeException;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException() {
        super("Không tìm thấy tài nguyên.");
    }
}
