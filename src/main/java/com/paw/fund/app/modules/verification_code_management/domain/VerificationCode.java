package com.paw.fund.app.modules.verification_code_management.domain;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Random;

@Builder
public record VerificationCode(
        Long verificationCodeId,
        Long accountId,
        String newEmail,
        String code,
        String typeCode,
        String typeName,
        Boolean isUsed,
        LocalDateTime expiredAt
) {
}
