package com.paw.fund.app.modules.category_management.domain;

import com.paw.fund.enums.EColor;
import lombok.Builder;

@Builder
public record PetColorCategory(
        String code,
        String name
) {
    public static PetColorCategory of(EColor color) {
        return PetColorCategory.builder()
                .code(color.getCode())
                .name(color.getName())
                .build();
    }
}
