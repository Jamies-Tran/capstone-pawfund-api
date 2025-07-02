package com.paw.fund.app.modules.shelter_management.domain.registration;

import lombok.Builder;
import lombok.With;

import java.time.LocalDateTime;

@Builder
public record ShelterRegistration(
        Long shelterRegistrationId,
        @With Long shelterId,
        @With Long accountId,
        Long processById,
        @With Long formResponseId,
        String rejectReason,
        LocalDateTime approvedAt,
        LocalDateTime rejectedAt,
        LocalDateTime requestAt,
        String statusCode,
        String statusName
) {
}
