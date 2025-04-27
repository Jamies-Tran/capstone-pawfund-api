package com.paw.fund.app.modules.pet_management.domain.breed;

import lombok.Builder;

@Builder
public record PetBreed(
        Long petBreedId,
        String breedCode,
        String breedName
) {
}
