package com.paw.fund.app.modules.shelter_management.domain.usecase.registration;

import com.paw.fund.app.modules.shelter_management.domain.Shelter;
import lombok.Builder;
import lombok.With;

@Builder
public record ShelterRegistrationCreate(
        String placeId,
        Long formResponseId,
        @With Shelter shelter
) {
    public static ShelterRegistrationCreate of(
            String placeId,
            Long formResponseId,
            Shelter shelter
    ) {
        return ShelterRegistrationCreate.builder()
                .placeId(placeId)
                .formResponseId(formResponseId)
                .shelter(shelter)
                .build();
    }
}
