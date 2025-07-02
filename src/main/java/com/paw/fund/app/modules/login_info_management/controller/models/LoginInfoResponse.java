package com.paw.fund.app.modules.login_info_management.controller.models;

import com.paw.fund.common.category.GeometryResponse;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record LoginInfoResponse(
        Long loginInfoId,
        String accessToken,
        String refreshToken,
        GeometryResponse geometry,
        LocalDateTime accessExpiredAt,
        LocalDateTime refreshExpiredAt,
        AccountResponse account
) {
}
