package com.paw.fund.app.modules.pet_intake_registration_management.domain.usecase;

import lombok.Builder;

@Builder
public record PetIntakeRegistrationId(Long value) {
    public static PetIntakeRegistrationId of(Long value) {
        return PetIntakeRegistrationId.builder()
                .value(value)
                .build();
    }
}
