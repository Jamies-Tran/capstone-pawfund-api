package com.paw.fund.app.modules.shelter_assignment_management.domain;

import com.paw.fund.app.modules.pet_intake_registration_management.domain.PetIntakeRegistration;
import com.paw.fund.app.modules.shelter_management.domain.Shelter;
import lombok.Builder;
import lombok.With;

import java.time.LocalDateTime;

@Builder
public record ShelterAssignment(
        Long shelterAssignmentId,
        Long shelterId,
        @With Shelter shelter,
        Long petIntakeRegistrationId,
        @With PetIntakeRegistration petIntakeRegistration,
        String reason,
        String statusCode,
        String statusName,
        LocalDateTime createdAt,
        @With ShelterAssignmentAction action
) {
}
