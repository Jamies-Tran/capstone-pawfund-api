package com.paw.fund.app.modules.form_management.domain.answer;

import com.paw.fund.app.modules.form_management.domain.option.Option;
import lombok.Builder;
import lombok.With;

import java.util.List;

@Builder
public record Answer(
        Long answerId,
        @With Long formResponseId,
        Long questionId,
        String questionText,
        String answerText,
        @With List<Option> options
) {
}
