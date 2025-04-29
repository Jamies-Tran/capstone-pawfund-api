package com.paw.fund.app.modules.pet_management.controller.type.models;

import lombok.Builder;

@Builder
public record PetTypeResponse(
        Long petTypeId,
        String petTypeCode,
        String petTypeName,
        String statusCode,
        String statusName
) {
}
