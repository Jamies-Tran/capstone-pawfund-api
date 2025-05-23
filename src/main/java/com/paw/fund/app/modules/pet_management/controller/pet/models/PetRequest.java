package com.paw.fund.app.modules.pet_management.controller.pet.models;

import com.paw.fund.app.modules.pet_management.controller.pet.models.medias.CommonMediaRequest;
import com.paw.fund.app.modules.pet_management.domain.pet.hobby.PetHobby;
import lombok.Builder;
import lombok.With;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
public record PetRequest(
        Long shelterId,
        Long petBreedId,
        Long petTypeId,
        Long petIntakeRegistrationId,
        String petName,
        String colorCode,
        String colorName,
        String genderCode,
        String genderName,
        String description,
        LocalDate dateOfBirth,
        LocalDate receivedAt,
        List<PetHobbyRequest> hobbies,
        List<CommonMediaRequest> medias,
        BigDecimal adoptionCost,
        String statusCode,
        String statusName
) {
}
