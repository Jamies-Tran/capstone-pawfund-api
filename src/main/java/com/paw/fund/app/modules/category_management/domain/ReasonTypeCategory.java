package com.paw.fund.app.modules.category_management.domain;

import com.paw.fund.enums.EReasonType;
import lombok.Builder;

@Builder
public record ReasonTypeCategory(String code, String name) {
    public static ReasonTypeCategory of(EReasonType reasonType) {
        return ReasonTypeCategory.builder()
                .code(reasonType.getCode())
                .name(reasonType.getName())
                .build();
    }
}
