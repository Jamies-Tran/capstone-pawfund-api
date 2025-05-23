package com.paw.fund.app.modules.shelter_assignment_management.domain.usecase;

import lombok.Builder;

@Builder
public record ShelterAssignmentCancel(
        Long shelterAssignmentId,
        String cancelReason
) {
    public static ShelterAssignmentCancel of(Long shelterAssignmentId,
                                             String cancelReason) {
        return ShelterAssignmentCancel.builder()
                .shelterAssignmentId(shelterAssignmentId)
                .cancelReason(cancelReason)
                .build();
    }
}
