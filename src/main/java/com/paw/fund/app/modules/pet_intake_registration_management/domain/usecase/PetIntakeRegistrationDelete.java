package com.paw.fund.app.modules.pet_intake_registration_management.domain.usecase;

import lombok.Builder;

@Builder
public record PetIntakeRegistrationDelete(Long petIntakeRegistrationId, String phone) {
    public static PetIntakeRegistrationDelete of(Long petIntakeRegistrationId, String phone) {
        return PetIntakeRegistrationDelete.builder()
                .petIntakeRegistrationId(petIntakeRegistrationId)
                .phone(phone)
                .build();
    }
}
