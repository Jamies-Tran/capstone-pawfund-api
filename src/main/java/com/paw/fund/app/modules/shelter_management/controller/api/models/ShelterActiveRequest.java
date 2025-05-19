package com.paw.fund.app.modules.shelter_management.controller.api.models;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record ShelterActiveRequest(
        String description,
        @NotNull(message = "Vui lòng nhập số lượng thú cưng tối đa")
        Integer maximumPetCapacity,
        List<ShelterMediaRequest> medias
) {
}
