package com.paw.fund.app.modules.pet_management.domain.breed.usecase;

import com.paw.fund.app.modules.pet_management.domain.breed.PetBreed;
import lombok.Builder;

import java.util.List;

@Builder
public record PetBreedList(List<PetBreed> list) {
    public static PetBreedList of(List<PetBreed> list) {
        return PetBreedList.builder()
                .list(list)
                .build();
    }
}
