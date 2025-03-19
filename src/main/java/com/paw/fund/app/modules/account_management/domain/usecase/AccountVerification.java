package com.paw.fund.app.modules.account_management.domain.usecase;

import lombok.Builder;

@Builder
public record AccountVerification(
        Long accountId,
        String verificationCode
) {
    public static AccountVerification of(Long accountId, String verificationCode) {
        return AccountVerification.builder()
                .accountId(accountId)
                .verificationCode(verificationCode)
                .build();
    }
}
