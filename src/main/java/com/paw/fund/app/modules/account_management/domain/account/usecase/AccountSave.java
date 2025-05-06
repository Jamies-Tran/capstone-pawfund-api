package com.paw.fund.app.modules.account_management.domain.account.usecase;

import com.paw.fund.app.modules.account_management.domain.account.Account;
import lombok.Builder;

@Builder
public record AccountSave(Long shelterId, Account account) {
    public static AccountSave of(Long shelterId, Account account) {
        return AccountSave.builder()
                .shelterId(shelterId)
                .account(account)
                .build();
    }

    public static AccountSave of(Account account) {
        return AccountSave.builder()
                .account(account)
                .build();
    }
}
