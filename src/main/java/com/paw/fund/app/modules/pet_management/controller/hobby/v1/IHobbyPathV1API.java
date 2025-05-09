package com.paw.fund.app.modules.pet_management.controller.hobby.v1;

import com.paw.fund.app.modules.pet_management.controller.hobby.models.HobbyResponse;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/hobby/{hobbyId}")
@Tag(name = "Hobby V1", description = "QL sở thích")
public interface IHobbyPathV1API {
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Xem chi tiết sở thích thú cưng",
            description = """
                    - Xem chi tiết sở thích thú cưng
                    - [ADMIN - Quản trị viên]
                    """)
    ValueResponse<HobbyResponse> getHobbyDetail(@PathVariable Long hobbyId);

    @PatchMapping("/active")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Kích hoạt sở thích thú cưng",
            description = """
                    - Kích hoạt sở thích thú cưng
                    - [ADMIN - Quản trị viên]
                    """)
    ValueResponse<HobbyResponse> activeHobby(@PathVariable Long hobbyId);

    @PatchMapping("/block")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Khóa sở thích thú cưng",
            description = """
                    - Khóa sở thích thú cưng
                    - [ADMIN - Quản trị viên]
                    """)
    ValueResponse<HobbyResponse> blockHobby(@PathVariable Long hobbyId);

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Xóa sở thích thú cưng",
            description = """
                    - Xóa sở thích thú cưng
                    - [ADMIN - Quản trị viên]
                    """)
    ValueResponse<HobbyResponse> deleteHobby(@PathVariable Long hobbyId);
}
