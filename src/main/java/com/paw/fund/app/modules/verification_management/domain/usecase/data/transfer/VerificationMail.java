package com.paw.fund.app.modules.verification_management.domain.usecase.data.transfer;

import lombok.Builder;

@Builder
public record VerificationMail(String value) {
    public static VerificationMail of(String value) {
        return VerificationMail.builder()
                .value(value)
                .build();
    }
}
