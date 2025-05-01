package com.paw.fund.app.modules.pet_management.controller.pet.models;

import lombok.With;

public record PetHobbyResponse(
        Long petHobbyId,
        Long petId,
        Long hobbyId,
        HobbyResponse hobby
) {
}
