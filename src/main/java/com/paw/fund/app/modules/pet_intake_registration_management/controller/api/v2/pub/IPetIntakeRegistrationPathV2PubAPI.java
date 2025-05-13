package com.paw.fund.app.modules.pet_intake_registration_management.controller.api.v2.pub;


import com.paw.fund.app.modules.pet_intake_registration_management.controller.api.models.PetIntakeRegistrationResponse;
import com.paw.fund.app.modules.pet_intake_registration_management.controller.api.models.PetIntakeRegistrationUpdateRequest;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v2/public/pet-intake-registration/{petIntakeRegistrationId}")
@Tag(name = "Pet Intake Registration V2", description = "QL đăng ký tiếp nhận thú cưng")
public interface IPetIntakeRegistrationPathV2PubAPI {
    @PutMapping
    @Operation(
            summary = "Cập nhật đăng ký tiếp nhận thú cưng",
            description = """
                    - Cập nhật đăng ký tiếp nhận thú cưng
                    - [USER - Người dùng]
                    """)
    ValueResponse<PetIntakeRegistrationResponse> updatePetIntakeRegistration(
            @PathVariable
            Long petIntakeRegistrationId,

            @Valid @RequestBody
            PetIntakeRegistrationUpdateRequest request

    );

    @DeleteMapping
    @Operation(
            summary = "Xóa đăng ký tiếp nhận thú cưng",
            description = """
                    - Xóa đăng ký tiếp nhận thú cưng
                    - [USER - Người dùng (xác mình bằng sđt)]
                    """)
    ValueResponse<?> deletePetIntakeRegistration(
            @PathVariable
            Long petIntakeRegistrationId,
            @RequestHeader("X-Delete-Key")
            String phone);
}
