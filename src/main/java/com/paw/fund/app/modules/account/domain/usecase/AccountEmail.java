package com.paw.fund.app.modules.account.domain.usecase;

import lombok.Builder;

@Builder
public record AccountEmail(String value) {
    public static AccountEmail of(String value) {
        return AccountEmail.builder().value(value).build();
    }
}
