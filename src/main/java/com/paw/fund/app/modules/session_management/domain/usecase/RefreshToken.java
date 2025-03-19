package com.paw.fund.app.modules.session_management.domain.usecase;

import lombok.Builder;

@Builder
public record RefreshToken(String value) {
    public static RefreshToken of(String value) {
        return RefreshToken.builder()
                .value(value)
                .build();
    }
}
