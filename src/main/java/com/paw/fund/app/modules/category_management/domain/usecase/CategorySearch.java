package com.paw.fund.app.modules.category_management.domain.usecase;

import lombok.Builder;

@Builder
public record CategorySearch(String value) {
    public static CategorySearch of(String value) {
        return CategorySearch.builder()
                .value(value)
                .build();
    }
}
