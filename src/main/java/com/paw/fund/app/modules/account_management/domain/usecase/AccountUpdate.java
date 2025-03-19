package com.paw.fund.app.modules.account_management.domain.usecase;

import com.paw.fund.app.modules.account_management.domain.Account;
import lombok.Builder;

@Builder
public record AccountUpdate(
        Long accountId,
        Account account
) {
}
