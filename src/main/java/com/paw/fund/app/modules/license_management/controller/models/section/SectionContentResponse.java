package com.paw.fund.app.modules.license_management.controller.models.section;

import io.swagger.v3.oas.annotations.media.Schema;

public record SectionContentResponse(
        @Schema(defaultValue = "Nội dung của mục giấy phép")
        String content
) {
}
