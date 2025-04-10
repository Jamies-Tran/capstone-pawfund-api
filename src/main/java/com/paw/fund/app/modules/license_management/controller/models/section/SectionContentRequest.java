package com.paw.fund.app.modules.license_management.controller.models.section;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record SectionContentRequest(
        @NotNull(message = "Vui lòng nhập nội dung của mục giấy phép")
        @Schema(defaultValue = "Nội dung của mục giấy phép")
        String content
) {
}
