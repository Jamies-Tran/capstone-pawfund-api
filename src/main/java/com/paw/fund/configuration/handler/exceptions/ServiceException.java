package com.paw.fund.configuration.handler.exceptions;

import java.lang.RuntimeException;

public class ServiceException extends RuntimeException {
    public ServiceException() {
        super("Lỗi service");
    }
}
