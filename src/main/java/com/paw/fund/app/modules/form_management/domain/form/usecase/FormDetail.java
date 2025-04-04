package com.paw.fund.app.modules.form_management.domain.form.usecase;

import lombok.Builder;

@Builder
public record FormDetail(
        Long formId,
        FormQuestionSearchCriteria formQuestionSearchCriteria
) {
    public static FormDetail of(Long formId, FormQuestionSearchCriteria formQuestionSearchCriteria) {
        return FormDetail.builder()
                .formId(formId)
                .formQuestionSearchCriteria(formQuestionSearchCriteria)
                .build();
    }
}
