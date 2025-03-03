package com.paw.fund.utils.response;

import lombok.Builder;

import java.util.Map;

@Builder
public record FieldErrorResponse(
        Map<String, String> fieldValidationFails
) {
    public static FieldErrorResponse of(Map<String, String> fieldValidationFails) {
        return FieldErrorResponse.builder()
                .fieldValidationFails(fieldValidationFails)
                .build();
    }
}
