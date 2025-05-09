package com.paw.fund.app.modules.category_management.controller.pet.gender;

import com.paw.fund.app.modules.category_management.controller.pet.gender.models.PetGenderCategoryResponse;
import com.paw.fund.utils.response.ListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v1/public/category/pet-gender")
@Tag(name = "Category V1", description = "QL danh mục")
public interface IPetGenderCategoryV1PubAPI {
    @GetMapping
    @Operation(
            summary = "Danh mục giới tính của pet",
            description = """
                    - Xem danh mục giới tính của pet
                    """)
    ListResponse<PetGenderCategoryResponse> getPetGenderCategoryList(
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search
    );
}
