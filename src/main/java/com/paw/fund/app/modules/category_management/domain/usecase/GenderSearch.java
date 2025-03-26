package com.paw.fund.app.modules.category_management.domain.usecase;

import lombok.Builder;

@Builder
public record GenderSearch(String value) {
    public static GenderSearch of(String value) {
        return GenderSearch.builder()
                .value(value)
                .build();
    }
}
