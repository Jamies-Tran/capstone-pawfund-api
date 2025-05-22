package com.paw.fund.app.modules.shelter_assignment_management.domain.usecase;

import lombok.Builder;

@Builder
public record ShelterAssignmentReject(
        Long shelterAssignmentId,
        String cancelReason
) {
    public static ShelterAssignmentReject of(Long shelterAssignmentId,
                                             String cancelReason) {
        return ShelterAssignmentReject.builder()
                .shelterAssignmentId(shelterAssignmentId)
                .cancelReason(cancelReason)
                .build();
    }
}
