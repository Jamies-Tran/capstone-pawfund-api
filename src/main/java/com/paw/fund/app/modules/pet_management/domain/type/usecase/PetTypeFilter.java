package com.paw.fund.app.modules.pet_management.domain.type.usecase;

import com.paw.fund.utils.request.PageRequestCustom;
import lombok.Builder;

@Builder
public record PetTypeFilter(PetTypeSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
    public static PetTypeFilter of(PetTypeSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
        return PetTypeFilter.builder()
                .searchCriteria(searchCriteria)
                .pageRequestCustom(pageRequestCustom)
                .build();
    }
}
