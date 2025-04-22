package com.paw.fund.app.modules.shelter_management.controller.api.registration;

import com.paw.fund.app.modules.shelter_management.controller.api.registration.models.ShelterRegistrationRequest;
import com.paw.fund.app.modules.shelter_management.controller.api.registration.models.ShelterResponse;
import com.paw.fund.utils.response.ValueResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/shelter/register")
public interface IShelterRegistrationV1API {
    @PostMapping
    ValueResponse<ShelterResponse> registerShelter(
            @RequestBody @Valid ShelterRegistrationRequest request);
}
