package com.paw.fund.configuration.handler.exceptions;

public class NullPointerException extends java.lang.NullPointerException {
    public NullPointerException() {
        super("Lỗi thiếu thông tin");
    }


    public NullPointerException(String message) {
        super(message);
    }
}
