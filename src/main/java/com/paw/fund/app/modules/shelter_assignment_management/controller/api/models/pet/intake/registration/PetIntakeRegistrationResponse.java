package com.paw.fund.app.modules.shelter_assignment_management.controller.api.models.pet.intake.registration;

import java.time.LocalDateTime;

public record PetIntakeRegistrationResponse(
        Long petIntakeRegistrationId,
        Long petTypeId,
        String informerPhone,
        String petDescription,
        String reasonTypeCode,
        String reasonTypeName,
        String address,
        String canceledReason,
        String statusCode,
        String statusName,
        LocalDateTime createdAt,
        Long createdById,
        String createdByName
) {
}
