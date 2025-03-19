package com.paw.fund.app.modules.log_management.domain.account;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AccountActivityLog(
        Long accountActivityLogId,
        Long accountId,
        String actionCode,
        String actionName,
        LocalDateTime loggedAt
) {
}
