package com.paw.fund.app.modules.pet_intake_registration_management.controller.api.models.media;

import lombok.Builder;

@Builder
public record VerificationMediaUpdateRequest(
        Long verificationMediaId,
        String url
) {
}
