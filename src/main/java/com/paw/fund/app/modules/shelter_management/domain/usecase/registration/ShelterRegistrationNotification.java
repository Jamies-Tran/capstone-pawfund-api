package com.paw.fund.app.modules.shelter_management.domain.usecase.registration;

import com.paw.fund.app.modules.shelter_management.domain.registration.ShelterRegistration;
import lombok.Builder;

import java.util.List;

@Builder
public record ShelterRegistrationNotification(
        List<ShelterRegistration> registrations,
        Long totalRegistrations
) {
    public static ShelterRegistrationNotification ofEmpty() {
        return ShelterRegistrationNotification.builder()
                .registrations(List.of())
                .totalRegistrations(0L)
                .build();
    }
}
