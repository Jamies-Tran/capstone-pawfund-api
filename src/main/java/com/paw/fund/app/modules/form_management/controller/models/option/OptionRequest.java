package com.paw.fund.app.modules.form_management.controller.models.option;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record OptionRequest(
        @Schema(description = "Nội dung lựa chọn câu trả lời")
        String optionText
) {
}
