package com.paw.fund.app.modules.form_management.controller.form.v1.models.option;

import lombok.Builder;

@Builder
public record OptionResponse(
        Long optionId,
        Long questionId,
        String optionText
) {
}
