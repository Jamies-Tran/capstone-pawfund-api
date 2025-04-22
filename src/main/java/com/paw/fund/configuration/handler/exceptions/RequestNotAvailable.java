package com.paw.fund.configuration.handler.exceptions;

import java.lang.RuntimeException;

public class RequestNotAvailable extends RuntimeException {
    public RequestNotAvailable(String message) {
        super(message);
    }
}
