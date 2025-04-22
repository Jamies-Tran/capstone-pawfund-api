package com.paw.fund.app.modules.form_management.controller.form.v1.models.form.reply;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record AnswerRequest(
        Long answerId,
        Long questionId,
        String answerText,
        @JsonProperty("optionReplyList")
        List<OptionReplyRequest> options
) {
}
