package com.paw.fund.app.modules.license_management.controller.models;

import com.paw.fund.app.modules.license_management.controller.models.section.LicenseSectionRequest;
import com.paw.fund.app.modules.license_management.controller.models.section.LicenseSectionUpdateRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.List;

@Builder
public record LicenseUpdateRequest(
        String description,

        @NotNull(message = "Vui lòng chọn mã loại giấy phép")
        String licenseTypeCode,

        @NotNull(message = "Vui lòng chọn tên loại giấy phép")
        String licenseTypeName,

        @Size(min = 1, message = "Giấy phép phải có ít nhất 1 mục")
        @NotNull(message = "Vui lòng nhập các mục và nội dung của giấy phép")
        @Valid
        List<LicenseSectionUpdateRequest> licenseSections
) {
}
