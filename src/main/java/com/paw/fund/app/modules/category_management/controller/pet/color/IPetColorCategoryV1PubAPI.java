package com.paw.fund.app.modules.category_management.controller.pet.color;

import com.paw.fund.app.modules.category_management.controller.pet.color.models.PetColorCategoryResponse;
import com.paw.fund.utils.response.ListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v1/public/category/pet-color")
@Tag(name = "Category V1", description = "QL danh mục")
public interface IPetColorCategoryV1PubAPI {
    @GetMapping
    @Operation(
            summary = "Danh mục màu lông của pet",
            description = """
                    - Xem danh mục màu lông của pet
                    """)
    ListResponse<PetColorCategoryResponse> getPetColorCategoryList(
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search
    );
}
