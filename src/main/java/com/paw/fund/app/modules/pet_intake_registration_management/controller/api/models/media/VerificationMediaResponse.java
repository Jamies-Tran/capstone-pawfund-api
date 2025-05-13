package com.paw.fund.app.modules.pet_intake_registration_management.controller.api.models.media;

import lombok.Builder;
import lombok.With;

@Builder
public record VerificationMediaResponse(
        Long verificationMediaId,
        Long petIntakeRegistrationId,
        String url,
        String mediaTypeCode,
        String mediaTypeName
) {
}
