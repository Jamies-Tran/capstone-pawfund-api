package com.paw.fund.app.modules.category_management.controller.shelter.assignment.status;

import com.paw.fund.app.modules.category_management.controller.shelter.assignment.status.models.ShelterAssignmentStatusCategoryResponse;
import com.paw.fund.utils.response.ListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v1/public/category/shelter-assignment-status")
@Tag(name = "Category V1", description = "QL danh mục")
public interface IShelterAssignmentStatusCategoryV1PubAPI {
    @GetMapping
    @Operation(
            summary = "Danh mục trạng thái phân công trung tâm cứu trợ",
            description = """
                    - Tìm kiếm danh sách danh mục trạng thái phân công trung tâm cứu trợ
                    """)
    ListResponse<ShelterAssignmentStatusCategoryResponse> getShelterAssignmentStatusCategoryList(
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search
    );
}
