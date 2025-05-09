package com.paw.fund.app.modules.category_management.domain;

import com.paw.fund.enums.EPetGender;
import lombok.Builder;

@Builder
public record PetGenderCategory(String code, String name) {
    public static PetGenderCategory of(EPetGender petGender) {
        return PetGenderCategory.builder()
                .code(petGender.getCode())
                .name(petGender.getName())
                .build();
    }
}
