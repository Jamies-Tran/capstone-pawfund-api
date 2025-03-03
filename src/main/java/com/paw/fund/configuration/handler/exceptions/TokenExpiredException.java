package com.paw.fund.configuration.handler.exceptions;

public class TokenExpiredException extends RuntimeException {
    public TokenExpiredException(String message) {
        super(message);
    }

    public TokenExpiredException() {
        super("Phiên đăng nhập đã hết hạn.");
    }
}
