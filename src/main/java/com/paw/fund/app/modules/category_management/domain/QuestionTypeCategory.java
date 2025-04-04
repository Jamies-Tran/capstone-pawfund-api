package com.paw.fund.app.modules.category_management.domain;

import com.paw.fund.enums.EQuestionType;
import lombok.Builder;

@Builder
public record QuestionTypeCategory(
        String code,
        String name
) {
    public static QuestionTypeCategory of(EQuestionType questionType) {
        return QuestionTypeCategory.builder()
                .code(questionType.getCode())
                .name(questionType.getName())
                .build();
    }
}
