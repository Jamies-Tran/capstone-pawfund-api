package com.paw.fund.configuration.handler.exceptions;

public class AccessDeniedException extends org.springframework.security.access.AccessDeniedException {
    public AccessDeniedException() {
        super("Bạn chưa xác thực tài khoản", null);
    }
}
