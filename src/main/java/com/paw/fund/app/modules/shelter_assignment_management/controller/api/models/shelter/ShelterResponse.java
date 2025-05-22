package com.paw.fund.app.modules.shelter_assignment_management.controller.api.models.shelter;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ShelterResponse(
        Long shelterId,
        String shelterCode,
        String shelterName,
        LocalDateTime dateOfPub,
        String email,
        String hotline,
        String address
) {
}
