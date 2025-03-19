package com.paw.fund.app.modules.session_management.controller.models;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record SessionResponse(
        Long sessionId,
        String accessToken,
        String refreshToken,
        LocalDateTime accessExpiredAt,
        LocalDateTime refreshExpiredAt,
        AccountResponse account
) {
}
