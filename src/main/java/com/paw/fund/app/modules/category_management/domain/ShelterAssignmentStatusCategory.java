package com.paw.fund.app.modules.category_management.domain;

import com.paw.fund.enums.EShelterAssignmentStatus;
import lombok.Builder;

@Builder
public record ShelterAssignmentStatusCategory(String code, String name) {
    public static ShelterAssignmentStatusCategory of(EShelterAssignmentStatus status) {
        return ShelterAssignmentStatusCategory.builder()
                .code(status.getCode())
                .name(status.getName())
                .build();
    }
}
