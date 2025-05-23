package com.paw.fund.app.modules.pet_management.domain.pet;

import com.paw.fund.app.modules.media_management.domain.common.CommonMedia;
import com.paw.fund.app.modules.pet_management.domain.breed.PetBreed;
import com.paw.fund.app.modules.pet_management.domain.pet.hobby.PetHobby;
import com.paw.fund.app.modules.pet_management.domain.type.PetType;
import com.paw.fund.app.modules.pet_management.domain.type.usecase.PetTypeList;
import lombok.Builder;
import lombok.With;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
public record Pet(
        Long petId,
        Long shelterId,
        Long petBreedId,
        @With PetBreed petBreed,
        Long petTypeId,
        @With PetType petType,
        @With Long petIntakeRegistrationId,
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
        @With String receiveSourceCode,
        @With String receiveSourceName,
        BigDecimal adoptionCost,
        String statusCode,
        String statusName
) {
}
