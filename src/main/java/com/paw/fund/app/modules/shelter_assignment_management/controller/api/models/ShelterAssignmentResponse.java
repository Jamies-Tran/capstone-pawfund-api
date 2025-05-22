package com.paw.fund.app.modules.shelter_assignment_management.controller.api.models;

import com.paw.fund.app.modules.shelter_assignment_management.controller.api.models.pet.intake.registration.PetIntakeRegistrationResponse;
import com.paw.fund.app.modules.shelter_assignment_management.controller.api.models.shelter.ShelterResponse;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ShelterAssignmentResponse(
        Long shelterAssignmentId,
        Long shelterId,
        ShelterResponse shelter,
        Long petIntakeRegistrationId,
        PetIntakeRegistrationResponse petIntakeRegistration,
        String reason,
        String statusCode,
        String statusName,
        LocalDateTime createdAt
) {
}
