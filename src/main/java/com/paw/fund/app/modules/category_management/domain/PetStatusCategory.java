package com.paw.fund.app.modules.category_management.domain;

import com.paw.fund.enums.EPetStatus;
import lombok.Builder;

@Builder
public record PetStatusCategory(String code, String name) {
    public static PetStatusCategory of(EPetStatus status) {
        return PetStatusCategory.builder()
                .code(status.getCode())
                .name(status.getName())
                .build();
    }
}
