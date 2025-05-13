package com.paw.fund.app.modules.pet_intake_registration_management.domain.usecase;

import lombok.Builder;

@Builder
public record PetIntakeRegistrationInformerPhone(String value) {
    public static PetIntakeRegistrationInformerPhone of(String value) {
        return PetIntakeRegistrationInformerPhone.builder()
                .value(value)
                .build();
    }
}
