package com.paw.fund.app.modules.pet_management.domain.hobby.usecase;

import lombok.Builder;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;

@Builder
public record HobbySearchCriteria(
        String search,
        List<String> statusCodes
) {
    public static HobbySearchCriteria of(String search,
                                         List<String> statusCodes) {
        return HobbySearchCriteria.builder()
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
