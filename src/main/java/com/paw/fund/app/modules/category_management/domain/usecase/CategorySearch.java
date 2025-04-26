package com.paw.fund.app.modules.category_management.domain.usecase;

import lombok.Builder;

@Builder
public record CategorySearch(String value, Integer mapLimitRecord) {
    public static CategorySearch of(String value) {
        return CategorySearch.builder()
                .value(value)
                .build();
    }

    public static CategorySearch of(String value, Integer mapLimitRecord) {
        return CategorySearch.builder()
                .value(value)
                .mapLimitRecord(mapLimitRecord)
                .build();
    }
}
