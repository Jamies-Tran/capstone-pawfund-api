package com.paw.fund.app.modules.form_management.controller.form.v1.models.form.reply;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record FormReplyResponse(
        Long formResponseId,
        Long accountId,
        Long formId,
        LocalDateTime responseAt,
        List<AnswerResponse> answers
) {
}
