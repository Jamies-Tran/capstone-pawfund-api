package com.paw.fund.utils.response;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record ValueResponse<T> (
        T data,
        String status,
        Boolean success,
        String errorCode,
        String message,
        String module,
        String apiVersion
) {
    public static <T> ValueResponse <T> success(T data, HttpStatus responseStatus, String module, String apiVersion) {
        return ValueResponse.<T>builder()
                .data(data)
                .success(Boolean.TRUE)
                .message(responseStatus.getReasonPhrase())
                .status(String.valueOf(responseStatus.value()))
                .module(module)
                .apiVersion(apiVersion)
                .build();
    }

    public static <T> ValueResponse <T> error(T data, HttpStatus responseStatus, String errorCode, String apiVersion) {
        return ValueResponse.<T>builder()
                .data(data)
                .success(Boolean.FALSE)
                .errorCode(errorCode)
                .message(responseStatus.getReasonPhrase())
                .status(String.valueOf(responseStatus.value()))
                .module("")
                .apiVersion(apiVersion)
                .build();
    }
}
