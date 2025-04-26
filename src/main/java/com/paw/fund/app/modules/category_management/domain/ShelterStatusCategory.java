package com.paw.fund.app.modules.category_management.domain;

import com.paw.fund.enums.EShelterStatus;
import lombok.Builder;

@Builder
public record ShelterStatusCategory(
        String code,
        String name
) {
    public static ShelterStatusCategory of(EShelterStatus status) {
        return ShelterStatusCategory.builder()
                .code(status.getCode())
                .name(status.getName())
                .build();
    }
}
