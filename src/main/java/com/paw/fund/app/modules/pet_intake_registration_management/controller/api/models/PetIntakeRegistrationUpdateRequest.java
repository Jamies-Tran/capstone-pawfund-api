package com.paw.fund.app.modules.pet_intake_registration_management.controller.api.models;

import com.paw.fund.app.modules.pet_intake_registration_management.controller.api.models.media.VerificationMediaUpdateRequest;
import lombok.Builder;
import java.util.List;

@Builder
public record PetIntakeRegistrationUpdateRequest(
        Long petTypeId,
        String placeId,
        String informerPhone,
        String petDescription,
        String reasonTypeCode,
        String reasonTypeName,
        List<VerificationMediaUpdateRequest> medias
) {
}
