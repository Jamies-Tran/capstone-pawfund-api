package com.paw.fund.app.modules.shelter_assignment_management.domain.usecase;

import com.paw.fund.utils.request.PageRequestCustom;
import lombok.Builder;

@Builder
public record ShelterAssignmentFilter(ShelterAssignmentSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
    public static ShelterAssignmentFilter of(ShelterAssignmentSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
        return ShelterAssignmentFilter.builder()
                .searchCriteria(searchCriteria)
                .pageRequestCustom(pageRequestCustom)
                .build();
    }
}
