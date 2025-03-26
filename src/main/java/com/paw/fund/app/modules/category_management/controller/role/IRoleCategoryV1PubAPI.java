package com.paw.fund.app.modules.category_management.controller.role;

import com.paw.fund.app.modules.category_management.controller.role.models.RoleCategoryResponse;
import com.paw.fund.utils.response.ListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v1/public/category/role")
@Tag(name = "Category V1", description = "QL danh mục")
public interface IRoleCategoryV1PubAPI {
    @GetMapping
    @Operation(
            summary = "Danh mục phân quyền",
            description = """
                    - Tìm kiếm danh sách danh mục phân quyền
                    """)
    ListResponse<RoleCategoryResponse> getRoleList(
            @Schema(description = "Tìm kiếm theo tên của danh mục")
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search);
}
