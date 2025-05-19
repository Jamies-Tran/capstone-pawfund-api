package com.paw.fund.app.modules.shelter_management.controller.api.models.pet.type;

import lombok.Builder;

@Builder
public record PetTypeResponse(
        String petTypeCode,
        String petTypeName
) {
}
