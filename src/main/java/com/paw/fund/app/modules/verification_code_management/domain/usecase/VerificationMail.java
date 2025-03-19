package com.paw.fund.app.modules.verification_code_management.domain.usecase;

import com.paw.fund.app.modules.verification_code_management.domain.VerificationCode;
import lombok.Builder;

@Builder
public record VerificationMail(String value) {
    public static VerificationMail of(String value) {
        return VerificationMail.builder()
                .value(value)
                .build();
    }
}
