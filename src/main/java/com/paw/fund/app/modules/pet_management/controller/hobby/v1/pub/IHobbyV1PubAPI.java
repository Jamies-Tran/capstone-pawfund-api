package com.paw.fund.app.modules.pet_management.controller.hobby.v1.pub;

import com.paw.fund.app.modules.pet_management.controller.hobby.models.HobbyResponse;
import com.paw.fund.utils.response.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/v1/public/hobby")
@Tag(name = "Hobby V1", description = "QL sở thích")
public interface IHobbyV1PubAPI {
    @GetMapping
    @Operation(
            summary = "Xem danh sách giống thú cưng",
            description = """
                    - Xem danh sách giống thú cưng
                    - [USER - Người dùng]
                    """)
    PageResponse<HobbyResponse> getHobbyList(
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search,

            @RequestParam(required = false, value = "petTypeId", defaultValue = "")
            Long petTypeId,

            @RequestParam(required = false, value = "sorter", defaultValue = "hobbyName")
            String sorter,

            @RequestParam(required = false, value = "current", defaultValue = "0")
            Integer current,

            @RequestParam(required = false, value = "pageSize", defaultValue = "25")
            Integer pageSize
    );
}
