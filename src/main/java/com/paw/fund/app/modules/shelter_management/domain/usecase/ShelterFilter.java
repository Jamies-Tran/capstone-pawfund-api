package com.paw.fund.app.modules.shelter_management.domain.usecase;

import com.paw.fund.utils.request.PageRequestCustom;
import lombok.Builder;

@Builder
public record ShelterFilter(ShelterSearchCriteria searchCriteria,
                            PageRequestCustom pageRequestCustom) {
    public static ShelterFilter of(ShelterSearchCriteria searchCriteria,
                                   PageRequestCustom pageRequestCustom) {
        return ShelterFilter.builder()
                .searchCriteria(searchCriteria)
                .pageRequestCustom(pageRequestCustom)
                .build();
    }
}
