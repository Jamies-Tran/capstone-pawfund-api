package com.paw.fund.app.modules.category_management.domain;

import com.paw.fund.enums.EShelterRegistrationStatus;
import lombok.Builder;

@Builder
public record ShelterRegistrationStatusCategory(String code, String name) {
    public static ShelterRegistrationStatusCategory of(EShelterRegistrationStatus status) {
        return ShelterRegistrationStatusCategory.builder()
                .code(status.getCode())
                .name(status.getName())
                .build();
    }
}
