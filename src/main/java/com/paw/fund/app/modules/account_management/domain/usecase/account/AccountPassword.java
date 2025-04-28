package com.paw.fund.app.modules.account_management.domain.usecase.account;

import lombok.Builder;

@Builder
public record AccountPassword(String value) {
    public static AccountPassword of(String value) {
        return AccountPassword.builder().value(value).build();
    }
}
