package com.paw.fund.app.modules.adopt_registration_management.domain;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AdoptRegistration(
        Long adoptRegistrationId,
        Long petId,
        Long accountRoleId,
        Long formReplyId,
        String denyReason,
        String statusCode,
        String statusName,
        LocalDateTime createdDate
) {
}
