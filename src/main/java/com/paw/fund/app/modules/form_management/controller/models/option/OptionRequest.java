package com.paw.fund.app.modules.form_management.controller.models.option;

import lombok.Builder;

@Builder
public record OptionRequest(
        String optionText
) {
}
