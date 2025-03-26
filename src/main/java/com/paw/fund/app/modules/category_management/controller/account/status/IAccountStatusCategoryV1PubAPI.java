package com.paw.fund.app.modules.category_management.controller.account.status;

import com.paw.fund.app.modules.category_management.controller.account.status.models.AccountStatusCategoryResponse;
import com.paw.fund.utils.response.ListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v1/public/category/account-status")
@Tag(name = "Category V1", description = "QL danh mục")
public interface IAccountStatusCategoryV1PubAPI {
    @GetMapping
    @Operation(
            summary = "Danh mục trạng thái tài khoản",
            description = """
                    - Tìm kiếm danh sách danh mục trạng thái tài khoản
                    """)
    ListResponse<AccountStatusCategoryResponse> getAccountStatusList(
            @Schema(description = "Tìm kiếm theo tên của danh mục")
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search);
}
