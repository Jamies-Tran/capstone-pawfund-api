package com.paw.fund.app.modules.form_management.domain.form.reply;

import com.paw.fund.app.modules.form_management.domain.answer.Answer;
import lombok.Builder;
import lombok.With;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record FormReply(
        Long formResponseId,
        @With Long accountId,
        Long formId,
        LocalDateTime responseAt,
        @With List<Answer> answers
) {
}
