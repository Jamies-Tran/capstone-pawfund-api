package com.paw.fund.app.modules.pet_management.domain.type.usecase;

import com.paw.fund.app.modules.pet_management.domain.type.PetType;
import lombok.Builder;

@Builder
public record PetTypeUpdate(
        Long petTypeId,
        PetType petType
) {
    public static PetTypeUpdate of(Long petTypeId,
                                   PetType petType) {
        return PetTypeUpdate.builder()
                .petTypeId(petTypeId)
                .petType(petType)
                .build();
    }
}
