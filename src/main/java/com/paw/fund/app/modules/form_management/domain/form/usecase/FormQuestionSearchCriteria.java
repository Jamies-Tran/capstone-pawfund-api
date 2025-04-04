package com.paw.fund.app.modules.form_management.domain.form.usecase;

import lombok.Builder;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Builder
public record FormQuestionSearchCriteria(
        String questionText,
        List<String> questionTypeCodes
) {
    public static FormQuestionSearchCriteria of(String questionText, List<String> questionTypeCodes) {
        return FormQuestionSearchCriteria.builder()
                .questionText(questionText)
                .questionTypeCodes(questionTypeCodes)
                .build();
    }

    public Boolean isQuestionTextEmptyOrNull() {
        return !StringUtils.hasText(questionText);
    }

    public Boolean isQuestionTypeCodesEmptyOrNull() {
        return CollectionUtils.isEmpty(questionTypeCodes);
    }
}
