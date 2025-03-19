package com.paw.fund.app.modules.account_management.domain.usecase;

import lombok.Builder;

@Builder
public record AccountUpdatePassword(
        Long accountId,
        String password
) {
    public static AccountUpdatePassword of(Long accountId, String password) {
        return AccountUpdatePassword.builder()
                .accountId(accountId)
                .password(password)
                .build();
    }
}
