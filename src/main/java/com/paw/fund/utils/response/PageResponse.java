package com.paw.fund.utils.response;

import com.paw.fund.env.AppEnv;
import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
public record PageResponse<T>(
        List<T> data,
        Meta meta,
        String status,
        Boolean success,
        String errorCode,
        String message,
        String apiVersion
) {
    public static <T> PageResponse <T> success(List<T> data, Meta meta,
                                               HttpStatus responseStatus) {
        return PageResponse.<T>builder()
                .data(data)
                .meta(meta)
                .success(Boolean.TRUE)
                .message(responseStatus.getReasonPhrase())
                .status(String.valueOf(responseStatus.value()))
                .apiVersion(AppEnv.API_VERSION)
                .build();
    }
}
