package com.paw.fund.app.modules.log_management.domain.pet.usecase;

import com.paw.fund.utils.request.PageRequestCustom;
import lombok.Builder;

@Builder
public record PetActivityLogFilter(
        PetActivityLogSearchCriteria searchCriteria,
        PageRequestCustom pageRequestCustom
) {
    public static PetActivityLogFilter of(PetActivityLogSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
        return PetActivityLogFilter.builder()
                .searchCriteria(searchCriteria)
                .pageRequestCustom(pageRequestCustom)
                .build();
    }
}
