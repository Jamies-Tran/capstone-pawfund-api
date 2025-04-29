package com.paw.fund.app.modules.pet_management.domain.type;

import lombok.Builder;

@Builder
public record PetType(
        Long petTypeId,
        String petTypeCode,
        String petTypeName,
        String statusCode,
        String statusName
) {
}
