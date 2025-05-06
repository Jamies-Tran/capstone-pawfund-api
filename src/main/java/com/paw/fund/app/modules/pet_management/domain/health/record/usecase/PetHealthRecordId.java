package com.paw.fund.app.modules.pet_management.domain.health.record.usecase;

import lombok.Builder;

@Builder
public record PetHealthRecordId(Long value) {
    public static PetHealthRecordId of(Long value) {
        return PetHealthRecordId.builder()
                .value(value)
                .build();
    }
}
