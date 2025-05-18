package com.paw.fund.app.modules.pet_intake_registration_management.domain.usecase;

import lombok.Builder;

@Builder
public record PetIntakeRegistrationCancel(Long petIntakeRegistrationId,
                                          String canceledReason) {
    public static PetIntakeRegistrationCancel of(Long petIntakeRegistrationId,
                                                 String canceledReason) {
        return PetIntakeRegistrationCancel.builder()
                .petIntakeRegistrationId(petIntakeRegistrationId)
                .canceledReason(canceledReason)
                .build();
    }
}
