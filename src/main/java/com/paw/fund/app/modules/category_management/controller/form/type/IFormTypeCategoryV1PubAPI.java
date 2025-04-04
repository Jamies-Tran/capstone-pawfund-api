package com.paw.fund.app.modules.category_management.controller.form.type;

import com.paw.fund.app.modules.category_management.controller.form.type.models.FormTypeCategoryResponse;
import com.paw.fund.utils.response.ListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v1/public/category/form-type")
@Tag(name = "Category V1", description = "QL danh mục")
public interface IFormTypeCategoryV1PubAPI {
    @GetMapping
    @Operation(
            summary = "Danh mục loại form",
            description = """
                    - Tìm kiếm danh sách danh mục loại form
                    """)
    ListResponse<FormTypeCategoryResponse> getFormTypeList(
            @RequestParam(required = false, value = "search", defaultValue = "")
            @Schema(description = "Tìm kiếm theo tên của danh mục")
            String search);
}
