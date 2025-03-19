package com.paw.fund.app.modules.verification_code_management.controller.models;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record VerificationCodeResponse(
        Long verificationCodeId,
        Long accountId,
        String code,
        String typeCode,
        String typeName,
        Boolean isUsed,
        LocalDateTime expiredAt
) {
}
