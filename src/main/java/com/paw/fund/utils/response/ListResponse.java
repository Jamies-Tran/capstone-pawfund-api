package com.paw.fund.utils.response;

import com.paw.fund.env.AppEnv;
import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
public record ListResponse<T>(
        List<T> data,
        String status,
        Boolean success,
        String errorCode,
        String message,
        String apiVersion) {
    public static <T> ListResponse <T> success(List<T> data, HttpStatus responseStatus) {
        return ListResponse.<T>builder()
                .data(data)
                .success(Boolean.TRUE)
                .message(responseStatus.getReasonPhrase())
                .status(String.valueOf(responseStatus.value()))
                .apiVersion(AppEnv.API_VERSION)
                .build();
    }
}
