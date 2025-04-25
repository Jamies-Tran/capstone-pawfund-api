package com.paw.fund.app.modules.form_management.domain.question;

import com.paw.fund.app.modules.form_management.domain.option.Option;
import com.paw.fund.enums.EQuestionType;
import lombok.Builder;
import lombok.With;

import java.util.List;
import java.util.Objects;

@Builder
public record Question(
        Long questionId,
        @With Long formId,
        String questionText,
        @With List<Option> options,
        String questionTypeCode,
        String questionTypeName,
        Boolean isMultipleChoice,
        String statusCode,
        String statusName
) {
    public Question {
        isMultipleChoice = Objects.equals(questionTypeCode, EQuestionType.MULTIPLE_CHOICE.getCode());
    }
}
