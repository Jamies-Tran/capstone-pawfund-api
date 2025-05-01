package com.paw.fund.app.modules.pet_management.domain.type.usecase;

import com.paw.fund.app.modules.pet_management.domain.type.PetType;
import lombok.Builder;

import java.util.List;

@Builder
public record PetTypeList(List<PetType> list) {
    public static PetTypeList of(List<PetType> list) {
        return PetTypeList.builder()
                .list(list)
                .build();
    }
}
