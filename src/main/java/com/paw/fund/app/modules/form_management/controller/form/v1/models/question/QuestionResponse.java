package com.paw.fund.app.modules.form_management.controller.form.v1.models.question;

import com.paw.fund.app.modules.form_management.controller.form.v1.models.option.OptionResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record QuestionResponse(
        Long questionId,
        Long formId,
        String questionText,
        List<OptionResponse> options,
        String questionTypeCode,
        String questionTypeName
) {
}
