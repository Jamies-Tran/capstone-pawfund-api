package com.paw.fund.app.modules.pet_management.domain.breed.usecase;

import com.paw.fund.utils.request.PageRequestCustom;
import lombok.Builder;

@Builder
public record PetBreedFilter(PetBreedSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
    public static PetBreedFilter of(PetBreedSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
        return PetBreedFilter.builder()
                .searchCriteria(searchCriteria)
                .pageRequestCustom(pageRequestCustom)
                .build();
    }
}
