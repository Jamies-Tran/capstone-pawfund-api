package com.paw.fund.app.modules.shelter_management.domain.usecase.registration;

import lombok.Builder;

@Builder
public record ShelterRegistrationId(Long value) {
    public static ShelterRegistrationId of(Long value) {
        return ShelterRegistrationId.builder()
                .value(value)
                .build();
    }
}
