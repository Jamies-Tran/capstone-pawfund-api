package com.paw.fund.app.modules.pet_management.domain.pet;

import lombok.Builder;

@Builder
public record PetSummarizeInfo(
        Long shelterId,
        Integer total,
        String petTypeCode,
        String petTypeName
) {
}
