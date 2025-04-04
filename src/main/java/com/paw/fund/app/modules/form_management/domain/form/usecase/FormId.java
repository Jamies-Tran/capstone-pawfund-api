package com.paw.fund.app.modules.form_management.domain.form.usecase;

import lombok.Builder;

@Builder
public record FormId(Long value) {
    public static FormId of(Long value) {
        return FormId.builder()
                .value(value)
                .build();
    }
}
