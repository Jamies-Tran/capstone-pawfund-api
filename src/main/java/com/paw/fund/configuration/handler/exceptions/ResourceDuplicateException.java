package com.paw.fund.configuration.handler.exceptions;

import java.lang.RuntimeException;

public class ResourceDuplicateException extends RuntimeException {
    public ResourceDuplicateException(String message) {
        super(message);
    }

    public ResourceDuplicateException() {
        super("Tài nguyên bị trùng lập");
    }
}
