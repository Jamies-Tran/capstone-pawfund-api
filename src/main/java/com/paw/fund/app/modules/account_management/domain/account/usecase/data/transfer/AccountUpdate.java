package com.paw.fund.app.modules.account_management.domain.account.usecase.data.transfer;

import com.paw.fund.app.modules.account_management.domain.account.Account;
import lombok.Builder;

@Builder
public record AccountUpdate(
        Long accountId,
        Account account
) {
}
