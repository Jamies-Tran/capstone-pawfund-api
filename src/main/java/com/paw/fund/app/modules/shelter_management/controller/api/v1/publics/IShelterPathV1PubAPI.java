package com.paw.fund.app.modules.shelter_management.controller.api.v1.publics;

import com.paw.fund.app.modules.shelter_management.controller.api.models.ShelterResponse;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/public/shelter/{shelterId}")
@Tag(name = "Shelter V1", description = "QL trung tâm cứu trợ")
public interface IShelterPathV1PubAPI {
    @GetMapping
    @Operation(
            summary = "Xem chi tiết trung tâm cứu trợ",
            description = """
                    - Người dùng xem chi tiết trung tâm cứu trợ
                    - [USER - Người dùng (đã xác thực | Chưa xác thực)]
                    """)
    ValueResponse<ShelterResponse> getShelterId(@PathVariable Long shelterId);
}
