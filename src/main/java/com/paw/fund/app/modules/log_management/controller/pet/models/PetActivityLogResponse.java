package com.paw.fund.app.modules.log_management.controller.pet.models;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PetActivityLogResponse(Long petActivityLogId,
                                     Long petId,
                                     String actionCode,
                                     String actionName,
                                     String description,
                                     LocalDateTime createdAt,
                                     Long createdById,
                                     String createdByName) {
}
