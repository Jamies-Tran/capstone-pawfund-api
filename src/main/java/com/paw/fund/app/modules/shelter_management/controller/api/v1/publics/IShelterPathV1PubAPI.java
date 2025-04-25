package com.paw.fund.app.modules.shelter_management.controller.api.v1.publics;

import com.paw.fund.app.modules.shelter_management.controller.api.models.ShelterResponse;
import com.paw.fund.utils.response.ValueResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/public/shelter/{shelterId}")
public interface IShelterPathV1PubAPI {
    @GetMapping
    ValueResponse<ShelterResponse> getShelterId(@PathVariable Long shelterId);
}
