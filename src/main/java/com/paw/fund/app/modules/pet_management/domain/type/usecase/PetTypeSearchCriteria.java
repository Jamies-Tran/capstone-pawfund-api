package com.paw.fund.app.modules.pet_management.domain.type.usecase;

import lombok.Builder;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Builder
public record PetTypeSearchCriteria(
        String search,
        List<String> statusCodes
) {
    public static PetTypeSearchCriteria of(String search, List<String> statusCodes) {
        return PetTypeSearchCriteria.builder()
                .search(search)
                .statusCodes(statusCodes)
                .build();
    }

    public Boolean isSearchNullOrEmpty() {
        return !StringUtils.hasText(search);
    }

    public Boolean isStatusCodesNullOrEmpty() {
        return CollectionUtils.isEmpty(statusCodes);
    }
}
