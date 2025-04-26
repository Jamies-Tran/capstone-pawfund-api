package com.paw.fund.app.modules.form_management.domain.form.usecase;

import lombok.Builder;

@Builder
public record FormReplyId(Long value) {
    public static FormReplyId of(Long value) {
        return FormReplyId.builder()
                .value(value)
                .build();
    }
}
