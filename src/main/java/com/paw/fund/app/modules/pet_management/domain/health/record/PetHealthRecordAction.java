package com.paw.fund.app.modules.pet_management.domain.health.record;

import lombok.Builder;

@Builder
public record PetHealthRecordAction(
        Boolean allowUpdate,
        Boolean allowDelete
) {
    public static PetHealthRecordAction of(Boolean allowUpdate, Boolean allowDelete) {
        return PetHealthRecordAction.builder()
                .allowUpdate(allowUpdate)
                .allowDelete(allowDelete)
                .build();
    }
}
