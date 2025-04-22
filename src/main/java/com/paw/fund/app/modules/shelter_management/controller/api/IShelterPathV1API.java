package com.paw.fund.app.modules.shelter_management.controller.api;

import com.paw.fund.app.modules.shelter_management.controller.api.models.ShelterActiveRequest;
import com.paw.fund.app.modules.shelter_management.controller.api.models.ShelterResponse;
import com.paw.fund.utils.response.ValueResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/shelter/{shelterId}")
public interface IShelterPathV1API {
    @PatchMapping("/active")
    ValueResponse<ShelterResponse> activeShelter(@PathVariable Long shelterId,
                                                 @RequestBody @Valid ShelterActiveRequest request);
}
