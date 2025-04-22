package com.paw.fund.app.modules.shelter_management.controller.api.registration.models;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ShelterRegistrationResponse(
        Long shelterRegistrationId,
        Long shelterId,
        Long accountId,
        String reason,
        LocalDateTime approvedAt,
        LocalDateTime rejectedAt,
        LocalDateTime requestAt,
        String statusCode,
        String statusName
) {
}
