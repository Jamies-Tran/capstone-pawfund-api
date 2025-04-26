package com.paw.fund.app.modules.shelter_management.controller.api.registration;

import com.paw.fund.app.modules.shelter_management.controller.api.registration.models.ShelterRegistrationRejectRequest;
import com.paw.fund.app.modules.shelter_management.controller.api.registration.models.ShelterRegistrationResponse;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/shelter/register/{shelterRegistrationId}")
@Tag(name = "Shelter Registration V1", description = "QL đăng ký trung tâm cứu trợ")
public interface IShelterRegisterPathV1API {
    @PatchMapping("/receive")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Tiếp nhận đăng ký trung tâm cứu trợ",
            description = """
                    - Admin tiếp nhận đăng ký trung tâm cứu trợ
                    - [ADMIN - Quản trị viên]
                    """)
    ValueResponse<ShelterRegistrationResponse> receiveShelter(@PathVariable Long shelterRegistrationId);

    @PatchMapping("/approve")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Duyệt đăng ký trung tâm cứu trợ",
            description = """
                    - Admin duyệt đăng ký trung tâm cứu trợ
                    - [ADMIN - Quản trị viên]
                    """)
    ValueResponse<ShelterRegistrationResponse> approveShelter(@PathVariable Long shelterRegistrationId);

    @PatchMapping("/reject")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Từ chối đăng ký trung tâm cứu trợ kèm theo lí do từ chối",
            description = """
                    - Admin từ chối đăng ký trung tâm cứu trợ kèm theo lí do từ chối
                    - [ADMIN - Quản trị viên]
                    """)
    ValueResponse<ShelterRegistrationResponse> rejectShelter(@PathVariable Long shelterRegistrationId,
                                                             @RequestBody @Valid ShelterRegistrationRejectRequest request);

    @GetMapping
    @Operation(
            summary = "Xem thông tin chi tiết đăng ký trung tâm cứu trợ",
            description = """
                    - Người dùng đã xác thực xem thông tin chi tiết đăng ký trung tâm cứu trợ
                    - [AUTHENTICATED - Người dùng đã xác thực]
                    """)
    ValueResponse<ShelterRegistrationResponse> getShelterRegistrationDetail(@PathVariable Long shelterRegistrationId);
}
