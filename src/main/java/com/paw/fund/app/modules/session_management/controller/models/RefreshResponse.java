package com.paw.fund.app.modules.session_management.controller.models;

import java.time.LocalDateTime;

public record RefreshResponse(
        String accessToken,
        LocalDateTime accessExpiredAt
) {
}
