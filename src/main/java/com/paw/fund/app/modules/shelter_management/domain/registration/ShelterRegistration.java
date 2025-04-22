package com.paw.fund.app.modules.shelter_management.domain.registration;

import lombok.Builder;
import lombok.With;

import java.time.LocalDateTime;

@Builder
public record ShelterRegistration(
        Long shelterRegistrationId,
        @With Long shelterId,
        @With Long accountId,
        @With Long formResponseId,
        String reason,
        LocalDateTime approvedAt,
        LocalDateTime rejectedAt,
        LocalDateTime requestAt
) {
}
