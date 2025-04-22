package com.paw.fund.app.modules.form_management.controller.form.v1.models.form.reply;

import lombok.Builder;

import java.util.List;

@Builder
public record FormReplyRequest(
        Long formId,
        List<AnswerRequest> answers
) {
}
