package com.paw.fund.app.modules.shelter_assignment_management.domain;

import lombok.Builder;

@Builder
public record ShelterAssignmentAction(
        Long shelterAssignmentId,
        Boolean allowUpdate,
        Boolean allowReceive,
        Boolean allowCancel,
        Boolean allowComplete
) {
    public static ShelterAssignmentAction ofDefault(Long shelterAssignmentId) {
        return ShelterAssignmentAction.builder()
                .shelterAssignmentId(shelterAssignmentId)
                .allowUpdate(false)
                .allowReceive(false)
                .allowCancel(false)
                .allowComplete(false)
                .build();
    }
}
