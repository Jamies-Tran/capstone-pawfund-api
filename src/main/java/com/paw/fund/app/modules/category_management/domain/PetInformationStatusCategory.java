package com.paw.fund.app.modules.category_management.domain;

import com.paw.fund.enums.EPetInformationStatus;
import lombok.Builder;

@Builder
public record PetInformationStatusCategory(String code, String name) {
    public static PetInformationStatusCategory of(EPetInformationStatus status) {
        return PetInformationStatusCategory.builder()
                .code(status.getCode())
                .name(status.getName())
                .build();
    }
}
