package com.paw.fund.app.modules.log_management.domain.pet;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PetActivityLog(
        Long petActivityLogId,
        Long petId,
        String actionCode,
        String actionName,
        String description,
        LocalDateTime createdAt,
        Long createdById,
        String createdByName
) {
}
