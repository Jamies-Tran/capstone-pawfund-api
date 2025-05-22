package com.paw.fund.app.modules.shelter_assignment_management.domain.usecase;

import com.paw.fund.app.modules.shelter_assignment_management.domain.ShelterAssignment;
import lombok.Builder;

@Builder
public record ShelterAssignmentUpdate(
        Long shelterAssignmentId,
        ShelterAssignment shelterAssignment
) {
    public static ShelterAssignmentUpdate of(Long shelterAssignmentId,
                                             ShelterAssignment shelterAssignment) {
        return ShelterAssignmentUpdate.builder()
                .shelterAssignmentId(shelterAssignmentId)
                .shelterAssignment(shelterAssignment)
                .build();
    }
}
