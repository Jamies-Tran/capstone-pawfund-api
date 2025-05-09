package com.paw.fund.app.modules.pet_management.controller.type.v1.pub;

import com.paw.fund.app.modules.pet_management.controller.type.models.PetTypeResponse;
import com.paw.fund.utils.response.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v1/public/pet/type")
@Tag(name = "Pet Type V1", description = "QL loại thú cưng")
public interface IPetTypeV1PubAPI {
    @GetMapping
    @Operation(
            summary = "Xem danh sách loại thú cưng",
            description = """
                    - Xem danh sách loại thú cưng
                    - [USER - Người dùng]
                    """)
    PageResponse<PetTypeResponse> getPetTypeList(
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search,

            @RequestParam(required = false, value = "current", defaultValue = "0")
            Integer current,

            @RequestParam(required = false, value = "pageSize", defaultValue = "25")
            Integer pageSize
    );
}
