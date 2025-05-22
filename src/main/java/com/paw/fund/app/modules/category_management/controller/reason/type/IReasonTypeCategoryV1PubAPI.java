package com.paw.fund.app.modules.category_management.controller.reason.type;

import com.paw.fund.app.modules.category_management.controller.reason.type.models.ReasonTypeCategoryResponse;
import com.paw.fund.utils.response.ListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v1/public/category/intake-reason-type")
@Tag(name = "Category V1", description = "QL danh mục")
public interface IReasonTypeCategoryV1PubAPI {
    @GetMapping
    @Operation(
            summary = "Danh mục loại lí do tiệp nhận thú cưng",
            description = """
                    - Tìm kiếm danh sách danh mục loại lí do tiệp nhận thú cưng
                    """)
    ListResponse<ReasonTypeCategoryResponse> getReasonTypeCategoryList(
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search
    );
}
