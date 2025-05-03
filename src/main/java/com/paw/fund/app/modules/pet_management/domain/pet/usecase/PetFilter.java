package com.paw.fund.app.modules.pet_management.domain.pet.usecase;

import com.paw.fund.utils.request.PageRequestCustom;
import lombok.Builder;

@Builder
public record PetFilter(PetSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
    public static PetFilter of(PetSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
        return PetFilter.builder()
                .searchCriteria(searchCriteria)
                .pageRequestCustom(pageRequestCustom)
                .build();
    }
}
