package com.paw.fund.app.modules.pet_intake_registration_management.controller.api.models;

import lombok.Builder;

@Builder
public record PetIntakeRegistrationActionResponse(
        Long petIntakeRegistrationId,
        Boolean allowUpdate,
        Boolean allowDelete,
        Boolean allowProcess,
        Boolean allowCancel,
        Boolean allowFinished
) {
}
