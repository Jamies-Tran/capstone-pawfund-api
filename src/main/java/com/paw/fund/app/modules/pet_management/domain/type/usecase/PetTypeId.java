package com.paw.fund.app.modules.pet_management.domain.type.usecase;

import lombok.Builder;

@Builder
public record PetTypeId(Long value) {
    public static PetTypeId of(Long value) {
        return PetTypeId.builder()
                .value(value)
                .build();
    }
}
