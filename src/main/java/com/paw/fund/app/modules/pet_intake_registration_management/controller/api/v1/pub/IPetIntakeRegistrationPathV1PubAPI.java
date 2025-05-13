package com.paw.fund.app.modules.pet_intake_registration_management.controller.api.v1.pub;

import com.paw.fund.app.modules.pet_intake_registration_management.controller.api.models.PetIntakeRegistrationResponse;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/public/pet-intake-registration/{petIntakeRegistrationId}")
@Tag(name = "Pet Intake Registration V1", description = "QL đăng ký tiếp nhận thú cưng")
public interface IPetIntakeRegistrationPathV1PubAPI {
    @GetMapping
    @Operation(
            summary = "Xem chi tiết đăng ký tiếp nhận thú cưng",
            description = """
                    - Xem chi tiết đăng ký tiếp nhận thú cưng
                    - [USER - Người dùng]
                    """)
    ValueResponse<PetIntakeRegistrationResponse> getPetIntakeRegistrationDetail(
            @PathVariable
            Long petIntakeRegistrationId
    );
}
