package com.paw.fund.app.modules.category_management.controller.pet.status;

import com.paw.fund.app.modules.category_management.controller.pet.status.models.PetStatusCategoryResponse;
import com.paw.fund.utils.response.ListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v1/public/category/pet-status")
@Tag(name = "Category V1", description = "QL danh mục")
public interface IPetStatusCategoryV1PubAPI {
    @GetMapping
    @Operation(
            summary = "Danh mục trạng thái của pet",
            description = """
                    - Xem danh mục trạng thái của pet
                    """)
    ListResponse<PetStatusCategoryResponse> getPetStatusList(
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search);
}
