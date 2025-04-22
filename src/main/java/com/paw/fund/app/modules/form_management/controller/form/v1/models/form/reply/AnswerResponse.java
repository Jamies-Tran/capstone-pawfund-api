package com.paw.fund.app.modules.form_management.controller.form.v1.models.form.reply;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record AnswerResponse(
        Long answerId,
        Long formResponseId,
        Long questionId,
        String questionText,
        String answerText,
        @JsonProperty("optionReplyList")
        List<OptionReplyResponse> options
) {
}
