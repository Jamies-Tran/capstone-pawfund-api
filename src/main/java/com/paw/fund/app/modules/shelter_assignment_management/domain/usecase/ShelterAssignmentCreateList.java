package com.paw.fund.app.modules.shelter_assignment_management.domain.usecase;

import lombok.Builder;

import java.util.List;

@Builder
public record ShelterAssignmentCreateList(
        Long petIntakeRegistrationId,
        List<Long> shelterIds
) {
    public static ShelterAssignmentCreateList of(Long petIntakeRegistrationId, List<Long> shelterIds) {
         return ShelterAssignmentCreateList.builder()
                 .petIntakeRegistrationId(petIntakeRegistrationId)
                 .shelterIds(shelterIds)
                 .build();
    }
}
