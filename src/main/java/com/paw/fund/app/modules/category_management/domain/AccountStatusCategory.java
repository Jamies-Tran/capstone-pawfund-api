package com.paw.fund.app.modules.category_management.domain;

import com.paw.fund.enums.EAccountStatus;
import lombok.Builder;

@Builder
public record AccountStatusCategory(
        String code,
        String name
) {
    public static AccountStatusCategory of(EAccountStatus status) {
        return AccountStatusCategory.builder()
                .code(status.getCode())
                .name(status.getName())
                .build();
    }
}
