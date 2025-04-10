package com.paw.fund.app.modules.license_management.controller.models.section;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.List;

@Builder
public record LicenseSectionUpdateRequest(
        @Schema(description = "ID mục giấy phép (null nếu là thêm mới)")
        Long licenseSectionId,

        @NotNull(message = "Vui lòng nhập tiêu đề của mục giấy phép")
        @Schema(description = "Tiêu đề mục giấy phép - required")
        String sectionTitle,

        @Size(min = 1, message = "Mục giấy phép phải có ít nhất 1 nội dung")
        @NotNull(message = "Vui lòng nhập nội dung của giấy phép")
        @Valid
        @Schema(description = "Các phần nội dung của giấy phép - required")
        List<SectionContentRequest> sectionContent,

        @Schema(description = "Thông tin có bắt buộc hay không")
        Boolean isRequired
) {
}
