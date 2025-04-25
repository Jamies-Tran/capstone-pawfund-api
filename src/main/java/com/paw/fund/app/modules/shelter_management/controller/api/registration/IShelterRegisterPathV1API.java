package com.paw.fund.app.modules.shelter_management.controller.api.registration;

import com.paw.fund.app.modules.shelter_management.controller.api.registration.models.ShelterRegistrationRejectRequest;
import com.paw.fund.app.modules.shelter_management.controller.api.registration.models.ShelterRegistrationResponse;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/shelter/register/{shelterRegisterId}")
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
    ValueResponse<ShelterRegistrationResponse> receiveShelter(@PathVariable Long shelterRegisterId);

    @PatchMapping("/approve")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Duyệt đăng ký trung tâm cứu trợ",
            description = """
                    - Admin duyệt đăng ký trung tâm cứu trợ
                    - [ADMIN - Quản trị viên]
                    """)
    ValueResponse<ShelterRegistrationResponse> approveShelter(@PathVariable Long shelterRegisterId);

    @PatchMapping("/reject")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Từ chối đăng ký trung tâm cứu trợ kèm theo lí do từ chối",
            description = """
                    - Admin từ chối đăng ký trung tâm cứu trợ kèm theo lí do từ chối
                    - [ADMIN - Quản trị viên]
                    """)
    ValueResponse<ShelterRegistrationResponse> rejectShelter(@PathVariable Long shelterRegisterId,
                                                             @RequestBody @Valid ShelterRegistrationRejectRequest request);
}
