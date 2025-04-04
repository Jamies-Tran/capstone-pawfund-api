package com.paw.fund.app.modules.category_management.domain;

import com.paw.fund.enums.EFormType;
import lombok.Builder;

@Builder
public record FormTypeCategory(
        String code,
        String name
) {
    public static FormTypeCategory of(EFormType formType) {
        return FormTypeCategory.builder()
                .code(formType.getCode())
                .name(formType.getName())
                .build();
    }
}
