package com.paw.fund.app.modules.pet_intake_registration_management.controller.api.v1.api;

import com.paw.fund.app.modules.pet_intake_registration_management.controller.api.models.PetIntakeRegistrationCancelReason;
import com.paw.fund.app.modules.pet_intake_registration_management.controller.api.models.PetIntakeRegistrationResponse;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/pet-intake-registration/{petIntakeRegistrationId}")
@Tag(name = "Pet Intake Registration V1", description = "QL đăng ký tiếp nhận thú cưng")
public interface IPetIntakeRegistrationPathV1API {
    @PutMapping("/process")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Xử lý đăng ký tiếp nhận thú cưng",
            description = """
                    - Xử lý đăng ký tiếp nhận thú cưng
                    - [Admin - Quản trị viên]
                    """)
    ValueResponse<PetIntakeRegistrationResponse> processPetIntakeRegistration(@PathVariable Long petIntakeRegistrationId);

    @PutMapping("/cancel")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Hủy đăng ký tiếp nhận thú cưng",
            description = """
                    - Hủy đăng ký tiếp nhận thú cưng
                    - [Admin - Quản trị viên]
                    """)
    ValueResponse<PetIntakeRegistrationResponse> cancelPetIntakeRegistration(
            @PathVariable
            Long petIntakeRegistrationId,

            @RequestBody @Valid
            PetIntakeRegistrationCancelReason petIntakeRegistrationCancelReason);
}
