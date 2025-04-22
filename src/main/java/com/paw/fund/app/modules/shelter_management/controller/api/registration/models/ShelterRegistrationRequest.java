package com.paw.fund.app.modules.shelter_management.controller.api.registration.models;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ShelterRegistrationRequest(
        String shelterName,
        String placeId,
        Long formResponseId,
        String hotline,
        String email,
        LocalDateTime dateOfPub
) {
}
