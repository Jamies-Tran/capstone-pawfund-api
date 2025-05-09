package com.paw.fund.app.modules.category_management.controller.pet.information.status;


import com.paw.fund.app.modules.category_management.controller.pet.information.status.models.PetInformationStatusCategoryResponse;
import com.paw.fund.utils.response.ListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v1/public/category/pet-information-status")
@Tag(name = "Category V1", description = "QL danh mục")
public interface IPetInformationStatusCategoryV1PubAPI {
    @GetMapping
    @Operation(
            summary = "Danh mục trạng thái của các thông tin liên quan tới pet (type, breed, hobby)",
            description = """
                    - Xem danh mục trạng thái của các thông tin liên quan tới pet (type, breed, hobby)
                    """)
    ListResponse<PetInformationStatusCategoryResponse> getPetInformationStatusCategoryList(
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search
    );
}
