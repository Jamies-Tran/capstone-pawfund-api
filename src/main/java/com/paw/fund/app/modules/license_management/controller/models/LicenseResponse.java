package com.paw.fund.app.modules.license_management.controller.models;

import com.paw.fund.app.modules.license_management.controller.models.section.LicenseSectionResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

public record LicenseResponse(
        @Schema(description = "ID giấy phép")
        Long licenseId,

        @Schema(description = "Mô tả giấy phép")
        String description,

        @Schema(description = "Mã loại giấy phép")
        String licenseTypeCode,

        @Schema(description = "Tên loại giấy phép")
        String licenseTypeName,

        @Schema(description = "Mã trạng thái giấy phép")
        String statusCode,

        @Schema(description = "Tên trạng thái giấy phép")
        String statusName,

        @Schema(description = "Các danh mục của giấy phép")
        List<LicenseSectionResponse> licenseSections
) {
}
