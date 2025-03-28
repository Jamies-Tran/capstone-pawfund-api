package com.paw.fund.app.modules.form_management.domain.question;

import com.paw.fund.app.modules.form_management.domain.option.Option;
import lombok.Builder;
import lombok.With;

import java.util.List;

@Builder
public record Question(
        Long questionId,
        @With Long formId,
        String questionText,
        @With List<Option> options,
        String questionTypeCode,
        String questionTypeName
) {
}
