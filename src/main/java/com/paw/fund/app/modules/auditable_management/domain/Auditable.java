package com.paw.fund.app.modules.auditable_management.domain;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record Auditable(
        Long createdById,
        String createdByName,
        LocalDateTime createdAt,
        Long updatedById,
        String updatedByName,
        LocalDateTime updatedAt
) {
}
