package com.paw.fund.utils.response;

import com.paw.fund.env.AppEnv;
import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record ValueResponse<T> (
        T data,
        String status,
        Boolean success,
        String errorCode,
        String message,
        String apiVersion
) {
    public static <T> ValueResponse <T> success(T data, HttpStatus responseStatus) {
        return ValueResponse.<T>builder()
                .data(data)
                .success(Boolean.TRUE)
                .message(responseStatus.getReasonPhrase())
                .status(String.valueOf(responseStatus.value()))
                .apiVersion(AppEnv.API_VERSION)
                .build();
    }

    public static <T> ValueResponse <T> error(T data, HttpStatus responseStatus, String errorCode) {
        return ValueResponse.<T>builder()
                .data(data)
                .success(Boolean.FALSE)
                .errorCode(errorCode)
                .message(responseStatus.getReasonPhrase())
                .status(String.valueOf(responseStatus.value()))
                .apiVersion(AppEnv.API_VERSION)
                .build();
    }
}
