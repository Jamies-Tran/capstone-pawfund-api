package com.paw.fund.app.modules.media_management.domain.verification;

import lombok.Builder;
import lombok.With;

@Builder
public record VerificationMedia(
        Long verificationMediaId,
        Long licenseId,
        Long postAdoptionVerificationId,
        Long adoptionRegistrationId,
        @With Long petIntakeRegistrationId,
        String url,
        @With String mediaTypeCode,
        @With String mediaTypeName
) {
}
