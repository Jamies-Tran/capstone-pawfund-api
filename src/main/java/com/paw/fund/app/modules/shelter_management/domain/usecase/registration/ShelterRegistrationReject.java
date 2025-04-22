package com.paw.fund.app.modules.shelter_management.domain.usecase.registration;

import lombok.Builder;

@Builder
public record ShelterRegistrationReject(
        Long shelterRegistrationId,
        String rejectReason
) {
    public static ShelterRegistrationReject of(Long shelterRegistrationId, String rejectReason) {
        return ShelterRegistrationReject.builder()
                .shelterRegistrationId(shelterRegistrationId)
                .rejectReason(rejectReason)
                .build();
    }
}
