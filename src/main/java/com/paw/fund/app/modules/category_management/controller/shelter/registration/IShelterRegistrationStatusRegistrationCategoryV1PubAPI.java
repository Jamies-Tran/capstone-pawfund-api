package com.paw.fund.app.modules.category_management.controller.shelter.registration;

import com.paw.fund.app.modules.category_management.controller.shelter.models.ShelterRegistrationStatusCategoryResponse;
import com.paw.fund.utils.response.ListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v1/public/category/shelter-registration-status")
@Tag(name = "Category V1", description = "QL danh mục")
public interface IShelterRegistrationStatusRegistrationCategoryV1PubAPI {
    @GetMapping
    @Operation(
            summary = "Danh mục trạng thái đăng ký trung tâm cứu trợ",
            description = """
                    - Tìm kiếm danh sách danh mục trạng thái đăng ký trung tâm cứu trợ
                    """)
    ListResponse<ShelterRegistrationStatusCategoryResponse> getShelterRegistrationStatusCategoryList(
            @RequestParam(required = false, value = "search", defaultValue = "") String search
    );
}
