package com.paw.fund.app.modules.license_management.controller.models.section;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
public record LicenseSectionResponse(
        @Schema(defaultValue = "ID mục giấy phép")
        Long licenseSectionId,

        @Schema(defaultValue = "ID giấy phép")
        Long licenseId,

        @Schema(defaultValue = "Tiêu đề mục")
        String sectionTitle,

        @Schema(defaultValue = "Các phần nội dung của mục")
        List<SectionContentResponse> sectionContent,

        @Schema(defaultValue = "Thông tin có bắt buộc hay không")
        Boolean isRequired
) {
}
