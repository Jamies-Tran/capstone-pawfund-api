package com.paw.fund.app.modules.form_management.domain.form.usecase;

import com.paw.fund.app.modules.form_management.domain.form.Form;
import lombok.Builder;

@Builder
public record FormUpdate(
        Long formId,
        Form form
) {
    public static FormUpdate of(Long formId, Form form) {
        return FormUpdate.builder()
                .formId(formId)
                .form(form)
                .build();
    }
}
