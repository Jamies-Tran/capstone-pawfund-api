package com.paw.fund.app.modules.license_management.controller.models.section;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.List;

@Builder
public record LicenseSectionUpdateRequest(
        Long licenseSectionId,

        @NotNull(message = "Vui lòng nhập tiêu đề của mục giấy phép")
        String sectionTitle,

        @Size(min = 1, message = "Mục giấy phép phải có ít nhất 1 nội dung")
        @NotNull(message = "Vui lòng nhập nội dung của giấy phép")
        @Valid
        List<SectionContentRequest> sectionContent,

        Boolean isRequired
) {
}
