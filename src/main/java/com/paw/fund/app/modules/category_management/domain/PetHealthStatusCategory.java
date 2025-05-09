package com.paw.fund.app.modules.category_management.domain;

import com.paw.fund.enums.EHealthStatus;
import lombok.Builder;

@Builder
public record PetHealthStatusCategory(String code, String name) {
    public static PetHealthStatusCategory of(EHealthStatus status) {
        return PetHealthStatusCategory.builder()
                .code(status.getCode())
                .name(status.getName())
                .build();
    }
}
