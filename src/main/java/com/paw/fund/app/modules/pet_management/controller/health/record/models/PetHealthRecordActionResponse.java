package com.paw.fund.app.modules.pet_management.controller.health.record.models;

import lombok.Builder;

@Builder
public record PetHealthRecordActionResponse(
        Boolean allowUpdate,
        Boolean allowDelete
) {
}
