package com.paw.fund.app.modules.login_info_management.domain.usecase.data.transfer;

import lombok.Builder;

@Builder
public record RefreshToken(String value) {
    public static RefreshToken of(String value) {
        return RefreshToken.builder()
                .value(value)
                .build();
    }
}
