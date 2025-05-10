package com.paw.fund.app.modules.category_management.controller.receive.source;

import com.paw.fund.app.modules.category_management.controller.receive.source.models.ReceiveSourceCategoryResponse;
import com.paw.fund.utils.response.ListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v1/public/category/receive-source")
@Tag(name = "Category V1", description = "QL danh mục")
public interface IReceiveSourceCategoryV1PubAPI {
    @GetMapping
    @Operation(
            summary = "Danh mục nguồn tiếp nhận thú cưng",
            description = """
                    - Tìm kiếm danh sách danh mục nguồn tiếp nhận thú cưng
                    """)
    ListResponse<ReceiveSourceCategoryResponse> getReceiveSourceCategoryList(
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search);
}
