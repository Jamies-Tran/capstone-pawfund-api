package com.paw.fund.app.modules.category_management.domain.usecase;

import lombok.Builder;

@Builder
public record AccountStatusSearch(String value) {
    public static AccountStatusSearch of(String value) {
        return AccountStatusSearch.builder()
                .value(value)
                .build();
    }
}
