package com.paw.fund.app.modules.category_management.controller.question.type.models;

import lombok.Builder;

@Builder
public record QuestionTypeCategoryResponse(
        String code,
        String name
) {
}
