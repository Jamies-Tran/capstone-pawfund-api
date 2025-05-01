package com.paw.fund.app.modules.pet_management.controller.pet.models;

import com.paw.fund.app.modules.pet_management.controller.pet.models.medias.CommonMediaResponse;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record PetResponse(
        Long petId,
        Long shelterId,
        Long petBreedId,
        Long petTypeId,
        String petCode,
        String petName,
        String colorCode,
        String colorName,
        String genderCode,
        String genderName,
        String description,
        LocalDate dateOfBirth,
        LocalDate receivedAt,
        List<PetHobbyResponse> hobbies,
        List<CommonMediaResponse> medias,
        String statusCode,
        String statusName
) {
}
