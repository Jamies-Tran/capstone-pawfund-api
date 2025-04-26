package com.paw.fund.app.modules.shelter_management.controller.api.v1;

import com.paw.fund.app.modules.shelter_management.controller.api.models.ShelterActiveRequest;
import com.paw.fund.app.modules.shelter_management.controller.api.models.ShelterResponse;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/shelter/{shelterId}")
@Tag(name = "Shelter V1", description = "QL trung tâm cứu trợ")
public interface IShelterPathV1API {
    @PatchMapping("/active")
    @Operation(
            summary = "Kích hoạt trung tâm cứu trợ",
            description = """
                    - Người dùng đã xác thực kích hoạt trung tâm cứu trợ
                    - [AUTHENTICATED - Người dùng đã xác thực]
                    """)
    ValueResponse<ShelterResponse> activeShelter(@PathVariable Long shelterId,
                                                 @RequestBody @Valid ShelterActiveRequest request);
}
