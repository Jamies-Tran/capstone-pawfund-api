package com.paw.fund.app.modules.pet_management.controller.pet.models;

import com.paw.fund.app.modules.pet_management.controller.breed.models.PetBreedResponse;
import com.paw.fund.app.modules.pet_management.controller.pet.models.medias.CommonMediaResponse;
import com.paw.fund.app.modules.pet_management.controller.type.models.PetTypeResponse;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record PetResponse(
        Long petId,
        Long shelterId,
        Long petBreedId,
        PetBreedResponse petBreed,
        Long petTypeId,
        PetTypeResponse petType,
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
        String receiveSourceCode,
        String receiveSourceName,
        String statusCode,
        String statusName
) {
}
