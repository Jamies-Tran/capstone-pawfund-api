package com.paw.fund.app.modules.form_management.domain.answer.option;

import com.paw.fund.app.modules.form_management.domain.answer.Answer;
import com.paw.fund.app.modules.form_management.domain.option.Option;
import lombok.Builder;
import lombok.With;

@Builder
public record AnswerOption(
        Long answerOptionId,
        Long answerId,
        Long optionId,
        String optionText,
        @With Option option,
        @With Answer answer
) {
}
