package com.paw.fund.app.modules.pet_management.domain.hobby.usecase;

import lombok.Builder;

@Builder
public record HobbyId(Long value) {
    public static HobbyId of(Long value) {
        return HobbyId.builder()
                .value(value)
                .build();
    }
}
