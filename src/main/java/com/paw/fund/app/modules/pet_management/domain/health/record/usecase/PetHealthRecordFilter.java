package com.paw.fund.app.modules.pet_management.domain.health.record.usecase;

import com.paw.fund.utils.request.PageRequestCustom;
import lombok.Builder;

@Builder
public record PetHealthRecordFilter(PetHealthRecordSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
    public static PetHealthRecordFilter of(PetHealthRecordSearchCriteria searchCriteria,
                                           PageRequestCustom pageRequestCustom) {
        return PetHealthRecordFilter.builder()
                .searchCriteria(searchCriteria)
                .pageRequestCustom(pageRequestCustom)
                .build();
    }
}
