package com.paw.fund.app.modules.pet_management.domain.breed.usecase;

import com.paw.fund.app.modules.pet_management.domain.breed.PetBreed;
import lombok.Builder;

@Builder
public record PetBreedUpdate(Long petBreedId, PetBreed petBreed) {
    public static PetBreedUpdate of(Long petBreedId, PetBreed petBreed) {
        return PetBreedUpdate.builder()
                .petBreedId(petBreedId)
                .petBreed(petBreed)
                .build();
    }
}
