package com.paw.fund.app.modules.pet_management.domain.breed;

import lombok.Builder;

@Builder
public record PetBreed(
        Long petBreedId,
        Long petTypeId,
        String breedCode,
        String breedName,
        String statusCode,
        String statusName
) {
}
