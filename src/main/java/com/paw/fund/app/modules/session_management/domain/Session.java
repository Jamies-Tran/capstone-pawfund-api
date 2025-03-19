package com.paw.fund.app.modules.session_management.domain;

import com.paw.fund.app.modules.account_management.domain.Account;
import lombok.Builder;
import lombok.With;

import java.time.LocalDateTime;

@Builder
public record Session(
        Long sessionId,
        Long accountId,
        @With String accessToken,
        String refreshToken,
        @With LocalDateTime accessExpiredAt,
        LocalDateTime refreshExpiredAt,
        @With Account account
) {
}
