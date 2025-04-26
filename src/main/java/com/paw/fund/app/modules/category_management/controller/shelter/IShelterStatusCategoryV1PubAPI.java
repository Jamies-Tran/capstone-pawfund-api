package com.paw.fund.app.modules.category_management.controller.shelter;

import com.paw.fund.app.modules.category_management.controller.shelter.models.ShelterStatusCategoryResponse;
import com.paw.fund.utils.response.ListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v1/public/category/shelter-status")
@Tag(name = "Category V1", description = "QL danh mục")
public interface IShelterStatusCategoryV1PubAPI {
    @GetMapping
    @Operation(
            summary = "Danh mục trạng thái trung tâm cứu trợ",
            description = """
                    - Tìm kiếm danh sách danh mục trạng thái trung tâm cứu trợ
                    """)
    ListResponse<ShelterStatusCategoryResponse> getShelterStatusCategoryList(
            @RequestParam(required = false, value = "search", defaultValue = "") String search
    );
}
