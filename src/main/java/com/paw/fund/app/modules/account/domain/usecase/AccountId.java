package com.paw.fund.app.modules.account.domain.usecase;

import lombok.Builder;

@Builder
public record AccountId(Long value) {
    public static AccountId of(Long value) {
        return AccountId.builder().value(value).build();
    }
}
