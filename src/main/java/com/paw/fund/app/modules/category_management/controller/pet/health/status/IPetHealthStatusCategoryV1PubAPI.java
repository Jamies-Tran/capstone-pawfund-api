package com.paw.fund.app.modules.category_management.controller.pet.health.status;

import com.paw.fund.app.modules.category_management.controller.pet.health.status.models.PetHealthStatusCategoryResponse;
import com.paw.fund.utils.response.ListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v1/public/category/pet-health-status")
@Tag(name = "Category V1", description = "QL danh mục")
public interface IPetHealthStatusCategoryV1PubAPI {
    @GetMapping
    @Operation(
            summary = "Danh mục tình trạng sức khỏe của pet",
            description = """
                    - Xem danh mục tình trạng sức khỏe của pet
                    """)
    ListResponse<PetHealthStatusCategoryResponse> getPetHealthStatusCategoryList(
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search);
}
