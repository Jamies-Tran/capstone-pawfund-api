package com.paw.fund.app.modules.shelter_management.domain.usecase.registration;

import lombok.Builder;

@Builder
public record ShelterRegistrationEmail(String value) {
    public static ShelterRegistrationEmail of(String value) {
        return ShelterRegistrationEmail.builder()
                .value(value)
                .build();
    }
}
