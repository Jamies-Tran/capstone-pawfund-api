package com.paw.fund.app.modules.pet_management.domain.pet;

import com.paw.fund.app.modules.media_management.domain.common.CommonMedia;
import com.paw.fund.app.modules.pet_management.domain.pet.hobby.PetHobby;
import lombok.Builder;
import lombok.With;

import java.time.LocalDate;
import java.util.List;

@Builder
public record Pet(
        Long petId,
        Long shelterId,
        Long petBreedId,
        Long petTypeId,
        @With String petCode,
        String petName,
        String colorCode,
        String colorName,
        String genderCode,
        String genderName,
        String description,
        LocalDate dateOfBirth,
        LocalDate receivedAt,
        @With List<PetHobby> hobbies,
        @With List<CommonMedia> medias,
        String statusCode,
        String statusName
) {
}
