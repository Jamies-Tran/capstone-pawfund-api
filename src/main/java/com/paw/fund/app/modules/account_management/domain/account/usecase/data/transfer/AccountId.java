package com.paw.fund.app.modules.account_management.domain.account.usecase.data.transfer;

import lombok.Builder;

@Builder
public record AccountId(Long value) {
    public static AccountId of(Long value) {
        return AccountId.builder().value(value).build();
    }
}
