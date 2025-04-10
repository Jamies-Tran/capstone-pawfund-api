package com.paw.fund.app.modules.category_management.controller.license.status;

import com.paw.fund.app.modules.category_management.controller.license.models.status.LicenseStatusCategoryResponse;
import com.paw.fund.utils.response.ListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v1/public/category/license-status")
@Tag(name = "Category V1", description = "QL danh mục")
public interface ILicenseStatusCategoryV1PubAPI {
    @GetMapping
    @Operation(
            summary = "Danh mục trạng thái giấy phép",
            description = """
                    - Tìm kiếm danh sách danh mục trạng thái giấy phép
                    """)
    ListResponse<LicenseStatusCategoryResponse> getLicenseStatusList(
            @RequestParam(required = false, value = "search", defaultValue = "") String search);
}
