package com.paw.fund.configuration.handler.exceptions;

import lombok.Getter;

import java.lang.RuntimeException;

@Getter
public class LoginException extends RuntimeException {
    private String code;
    private String message;

    public LoginException(String message) {
        super(message);
    }

    public LoginException() {
        super("thông tin đăng nhập không hợp lệ.");
    }

    public LoginException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
