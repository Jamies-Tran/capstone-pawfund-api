package com.paw.fund.app.modules.pet_management.domain.hobby.usecase;

import com.paw.fund.utils.request.PageRequestCustom;
import lombok.Builder;

@Builder
public record HobbyFilter(HobbySearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
    public static HobbyFilter of(HobbySearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
        return HobbyFilter.builder()
                .searchCriteria(searchCriteria)
                .pageRequestCustom(pageRequestCustom)
                .build();
    }
}
