package com.paw.fund.app.modules.form_management.controller.form.v1.models.form.reply;

import lombok.Builder;

@Builder
public record OptionReplyResponse(
        Long answerOptionId,
        Long optionId,
        String optionText
) {
}
