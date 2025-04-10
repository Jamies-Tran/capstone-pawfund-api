package com.paw.fund.app.modules.category_management.controller.form.status;

import com.paw.fund.app.modules.category_management.controller.form.models.status.FormStatusCategoryResponse;
import com.paw.fund.utils.response.ListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v1/public/category/form-status")
@Tag(name = "Category V1", description = "QL danh mục")
public interface IFormStatusCategoryV1PubAPI {
    @GetMapping
    @Operation(
            summary = "Danh mục trạng thái form",
            description = """
                    - Tìm kiếm danh sách danh mục trạng thái form
                    """)
    ListResponse<FormStatusCategoryResponse> findFormStatusList(
            @RequestParam(required = false, value = "search", defaultValue = "")
            @Schema(description = "Tìm kiếm theo tên của danh mục")
            String search);
}
