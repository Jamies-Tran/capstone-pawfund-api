package com.paw.fund.app.modules.account_management.domain.usecase;

import lombok.Builder;

@Builder
public record AccountVerification(
        String email,
        String verificationCode
) {
    public static AccountVerification of(String email, String verificationCode) {
        return AccountVerification.builder()
                .email(email)
                .verificationCode(verificationCode)
                .build();
    }

    public static AccountVerification of(String verificationCode) {
        return AccountVerification.builder()
                .verificationCode(verificationCode)
                .build();
    }
}
