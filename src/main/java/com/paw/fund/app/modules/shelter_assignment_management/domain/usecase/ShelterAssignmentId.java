package com.paw.fund.app.modules.shelter_assignment_management.domain.usecase;

import lombok.Builder;

@Builder
public record ShelterAssignmentId(Long value) {
    public static ShelterAssignmentId of(Long shelterAssignmentId) {
        return ShelterAssignmentId.builder()
                .value(shelterAssignmentId)
                .build();
    }
}
