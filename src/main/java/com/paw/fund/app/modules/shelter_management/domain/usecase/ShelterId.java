package com.paw.fund.app.modules.shelter_management.domain.usecase;

import com.paw.fund.enums.EShelterRegistrationStatus;
import lombok.Builder;

@Builder
public record ShelterId(
        Long value
) {
    public static ShelterId of(Long value) {
        return ShelterId.builder()
                .value(value)
                .build();
    }
}
