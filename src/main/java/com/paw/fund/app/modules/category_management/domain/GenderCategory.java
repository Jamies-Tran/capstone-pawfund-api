package com.paw.fund.app.modules.category_management.domain;

import com.paw.fund.enums.EGender;
import lombok.Builder;

@Builder
public record GenderCategory(
        String code,
        String name
) {
    public static GenderCategory of(EGender gender) {
        return GenderCategory.builder()
                .code(gender.getCode())
                .name(gender.getName())
                .build();
    }
}
