package com.paw.fund.app.modules.form_management.controller.models.form;

import com.paw.fund.app.modules.form_management.controller.models.question.QuestionResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record FormResponse(
        Long formId,
        String title,
        String description,
        String formTypeCode,
        String formTypeName,
        Integer questionCount,
        List<QuestionResponse> questions,
        String statusCode,
        String statusName
) {
}
