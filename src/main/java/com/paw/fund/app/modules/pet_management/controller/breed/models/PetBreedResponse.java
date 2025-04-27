package com.paw.fund.app.modules.pet_management.controller.breed.models;

import lombok.Builder;

@Builder
public record PetBreedResponse(
        Long petBreedId,
        String breedCode,
        String breedName
) {
}
