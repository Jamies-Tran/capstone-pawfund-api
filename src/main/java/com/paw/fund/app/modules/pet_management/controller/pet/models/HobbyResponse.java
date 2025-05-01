package com.paw.fund.app.modules.pet_management.controller.pet.models;

import lombok.Builder;

@Builder
public record HobbyResponse(
        Long hobbyId,
        String hobbyCode,
        String hobbyName
) {
}
