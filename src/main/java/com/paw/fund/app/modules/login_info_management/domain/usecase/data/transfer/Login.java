package com.paw.fund.app.modules.login_info_management.domain.usecase.data.transfer;

import lombok.Builder;
import lombok.With;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record Login(
        Long loginInfoId,
        Long accountId,
        String accountEmail,
        @With String accessToken,
        String refreshToken,
        BigDecimal longitude,
        BigDecimal latitude,
        @With LocalDateTime accessExpiredAt,
        LocalDateTime refreshExpiredAt,
        String statusCode,
        String statusName,
        @With Account account
) {
}
