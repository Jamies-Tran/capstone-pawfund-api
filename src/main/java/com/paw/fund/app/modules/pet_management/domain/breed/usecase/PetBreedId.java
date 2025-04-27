package com.paw.fund.app.modules.pet_management.domain.breed.usecase;

import lombok.Builder;

@Builder
public record PetBreedId(Long value) {
    public static PetBreedId of(Long value) {
        return PetBreedId.builder()
                .value(value)
                .build();
    }
}
