package com.paw.fund.app.modules.shelter_assignment_management.controller.api.models;

import lombok.Builder;

@Builder
public record ShelterAssignmentRequest(
        Long shelterId,
        Long petIntakeRegistrationId
) {
}
