package com.paw.fund.app.modules.pet_intake_registration_management.domain.usecase;

import com.paw.fund.app.modules.pet_intake_registration_management.domain.PetIntakeRegistration;
import lombok.Builder;

import java.util.List;
import java.util.Objects;

@Builder
public record PetIntakeRegistrationNotification(
        List<PetIntakeRegistration> registrations,
        Integer total
) {
    public PetIntakeRegistrationNotification {
        if (Objects.isNull(total)) {
            total = registrations.size();
        }
    }

    public static PetIntakeRegistrationNotification of(List<PetIntakeRegistration> petIntakeRegistrations) {
        return PetIntakeRegistrationNotification.builder()
                .registrations(petIntakeRegistrations)
                .build();
    }
}
