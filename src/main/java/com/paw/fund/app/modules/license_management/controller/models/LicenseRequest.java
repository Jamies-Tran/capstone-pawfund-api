package com.paw.fund.app.modules.license_management.controller.models;

import com.paw.fund.app.modules.license_management.controller.models.section.LicenseSectionRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record LicenseRequest(
        @Schema(description = "Mô tả giấy phép")
        String description,

        @NotNull(message = "Vui lòng chọn mã loại giấy phép")
        @Schema(description = "Mã loại giấy phép - required (QL loại giấy phép)")
        String licenseTypeCode,

        @NotNull(message = "Vui lòng chọn tên loại giấy phép")
        @Schema(description = "Tên loại giấy phép - required (QL loại giấy phép)")
        String licenseTypeName,

        @Size(min = 1, message = "Giấy phép phải có ít nhất 1 mục")
        @NotNull(message = "Vui lòng nhập các mục và nội dung của giấy phép")
        @Valid
        @Schema(description = "Các danh mục của giấy phép - required")
        List<LicenseSectionRequest> licenseSections
) {
}
