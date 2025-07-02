package com.paw.fund.app.modules.verification_management.domain.usecase.data.transfer;

import com.paw.fund.enums.EVerificationType;
import lombok.Builder;

@Builder
public record VerificationCriteria(
        String code,
        Long accountId,
        EVerificationType verificationType
) {
    public static VerificationCriteria of(String code, Long accountId, EVerificationType verificationType) {
        return VerificationCriteria.builder()
                .code(code)
                .accountId(accountId)
                .verificationType(verificationType)
                .build();
    }
}
