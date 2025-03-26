package com.paw.fund.app.modules.category_management.domain.usecase;

import lombok.Builder;

@Builder
public record RoleSearch(String value) {
    public static RoleSearch of(String value) {
        return RoleSearch.builder()
                .value(value)
                .build();
    }
}
