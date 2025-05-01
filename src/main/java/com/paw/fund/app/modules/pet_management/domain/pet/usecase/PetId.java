package com.paw.fund.app.modules.pet_management.domain.pet.usecase;

import lombok.Builder;

@Builder
public record PetId(Long value) {
    public static PetId of(Long value) {
        return PetId.builder()
                .value(value)
                .build();
    }
}
