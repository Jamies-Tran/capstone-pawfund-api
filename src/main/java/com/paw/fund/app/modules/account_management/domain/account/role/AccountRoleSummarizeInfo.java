package com.paw.fund.app.modules.account_management.domain.account.role;

import lombok.Builder;

@Builder
public record AccountRoleSummarizeInfo(
        Long shelterId,
        Integer totalStaff
) {
}
