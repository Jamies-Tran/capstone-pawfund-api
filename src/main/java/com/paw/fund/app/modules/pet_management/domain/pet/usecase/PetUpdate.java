package com.paw.fund.app.modules.pet_management.domain.pet.usecase;

import com.paw.fund.app.modules.pet_management.domain.pet.Pet;
import lombok.Builder;

@Builder
public record PetUpdate(
        Long petId,
        Pet pet
) {
    public static PetUpdate of(Long petId, Pet pet) {
        return PetUpdate.builder()
                .petId(petId)
                .pet(pet)
                .build();
    }
}
