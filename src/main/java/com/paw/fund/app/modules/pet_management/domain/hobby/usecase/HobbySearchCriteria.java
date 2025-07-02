package com.paw.fund.app.modules.pet_management.domain.hobby.usecase;

import lombok.Builder;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Builder
public record HobbySearchCriteria(
        String search,
        Long petTypeId,
        List<String> statusCodes
) {
    public static HobbySearchCriteria of(String search,
                                         Long petTypeId,
                                         List<String> statusCodes) {
        return HobbySearchCriteria.builder()
                .search(search)
                .petTypeId(petTypeId)
                .statusCodes(statusCodes)
                .build();
    }

    public Boolean isSearchNullOrEmpty() {
        return !StringUtils.hasText(search);
    }

    public Boolean isStatusCodesNullOrEmpty() {
        return CollectionUtils.isEmpty(statusCodes);
    }

    public Boolean isPetTypeIdNullOrEmpty() {
        return Objects.isNull(petTypeId) || petTypeId < 0L;
    }
}
