package com.paw.fund.app.modules.pet_management.controller.pet.models;

import com.paw.fund.app.modules.pet_management.controller.pet.models.medias.CommonMediaRequest;
import com.paw.fund.app.modules.pet_management.controller.pet.models.medias.CommonMediaUpdateRequest;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record PetUpdateRequest(
        Long petBreedId,
        Long petTypeId,
        String petName,
        String colorCode,
        String colorName,
        String genderCode,
        String genderName,
        String description,
        LocalDate dateOfBirth,
        LocalDate receivedAt,
        List<PetHobbyUpdateRequest> hobbies,
        List<CommonMediaUpdateRequest> medias
) {
}
