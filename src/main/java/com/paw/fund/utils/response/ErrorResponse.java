package com.paw.fund.utils.response;

import lombok.Builder;

@Builder
public record ErrorResponse(
        Integer statusCode,
        String status,
        String errorCode,
        String errorMessage
) {
    public static ErrorResponse of(Integer statusCode,
                                   String status,
                                   String errorCode,
                                   String errorMessage) {
        return ErrorResponse.builder()
                .statusCode(statusCode)
                .status(status)
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .build();
    }
}
