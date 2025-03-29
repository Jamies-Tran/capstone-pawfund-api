package com.paw.fund.app.modules.form_management.domain.form;

import com.paw.fund.app.modules.form_management.domain.question.Question;
import lombok.Builder;
import lombok.With;

import java.util.List;

@Builder
public record Form(
        Long formId,
        String title,
        String description,
        @With String formTypeCode,
        @With String formTypeName,
        @With List<Question> questions
) {
}
