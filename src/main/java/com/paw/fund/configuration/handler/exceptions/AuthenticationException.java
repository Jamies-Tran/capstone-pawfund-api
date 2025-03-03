package com.paw.fund.configuration.handler.exceptions;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException() {
        super("Xác thực tài khoản thất bại.");
    }
}
