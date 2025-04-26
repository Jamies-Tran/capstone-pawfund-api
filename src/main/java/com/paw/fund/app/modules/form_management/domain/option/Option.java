package com.paw.fund.app.modules.form_management.domain.option;

import lombok.Builder;
import lombok.With;

@Builder
public record Option(
        Long optionId,
        @With Long questionId,
        @With Long answerOptionId,
        String optionText,
        String statusCode,
        String statusName
) {
}
