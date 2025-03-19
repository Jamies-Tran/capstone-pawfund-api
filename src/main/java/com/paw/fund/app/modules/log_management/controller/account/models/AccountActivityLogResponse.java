package com.paw.fund.app.modules.log_management.controller.account.models;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AccountActivityLogResponse(
        Long accountActivityLogId,
        Long accountId,
        String actionCode,
        String actionName,
        LocalDateTime loggedAt
) {
}
