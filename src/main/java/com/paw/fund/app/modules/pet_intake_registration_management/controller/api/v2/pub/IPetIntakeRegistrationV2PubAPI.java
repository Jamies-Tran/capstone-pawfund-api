package com.paw.fund.app.modules.pet_intake_registration_management.controller.api.v2.pub;

import com.paw.fund.app.modules.pet_intake_registration_management.controller.api.models.PetIntakeRegistrationRequest;
import com.paw.fund.app.modules.pet_intake_registration_management.controller.api.models.PetIntakeRegistrationResponse;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v2/public/pet-intake-registration")
@Tag(name = "Pet Intake Registration V2", description = "QL đăng ký tiếp nhận thú cưng")
public interface IPetIntakeRegistrationV2PubAPI {
    @PostMapping
    @Operation(
            summary = "Đăng ký tiếp nhận thú cưng",
            description = """
                    - Đăng ký tiếp nhận thú cưng
                    - [USER - Người dùng]
                    """)
    ValueResponse<PetIntakeRegistrationResponse> createPetIntakeRegistration(
            @Valid @RequestBody
            PetIntakeRegistrationRequest request);
}
