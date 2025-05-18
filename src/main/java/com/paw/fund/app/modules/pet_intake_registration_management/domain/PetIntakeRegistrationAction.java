package com.paw.fund.app.modules.pet_intake_registration_management.domain;

import lombok.Builder;

@Builder
public record PetIntakeRegistrationAction(
        Long petIntakeRegistrationId,
        Boolean allowUpdate,
        Boolean allowDelete,
        Boolean allowProcess,
        Boolean allowCancel,
        Boolean allowFinished
) {
    public static PetIntakeRegistrationAction ofDefault() {
        return PetIntakeRegistrationAction.builder()
                .allowUpdate(false)
                .allowDelete(false)
                .allowProcess(false)
                .allowCancel(false)
                .allowFinished(false)
                .build();
    }
}
