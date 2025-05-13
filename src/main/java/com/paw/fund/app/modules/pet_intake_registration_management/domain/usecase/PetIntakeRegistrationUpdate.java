package com.paw.fund.app.modules.pet_intake_registration_management.domain.usecase;

import com.paw.fund.app.modules.pet_intake_registration_management.domain.PetIntakeRegistration;
import lombok.Builder;

@Builder
public record PetIntakeRegistrationUpdate(Long petIntakeRegistrationId,
                                          PetIntakeRegistration petIntakeRegistration) {
    public static PetIntakeRegistrationUpdate of(Long petIntakeRegistrationId,
                                                 PetIntakeRegistration petIntakeRegistration) {
        return PetIntakeRegistrationUpdate.builder()
                .petIntakeRegistrationId(petIntakeRegistrationId)
                .petIntakeRegistration(petIntakeRegistration)
                .build();
    }
}
