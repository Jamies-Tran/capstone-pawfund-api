package com.paw.fund.app.modules.category_management.domain;

import com.paw.fund.enums.EFormStatus;
import lombok.Builder;

@Builder
public record FormStatusCategory(
        String code,
        String name
) {
    public static FormStatusCategory of(EFormStatus formStatus) {
        return FormStatusCategory.builder()
                .code(formStatus.getCode())
                .name(formStatus.getName())
                .build();
    }
}
