package com.paw.fund.app.modules.shelter_management.controller.api.models;

import lombok.Builder;

import java.util.List;

@Builder
public record ShelterActiveRequest(
        String description,
        List<ShelterMediaRequest> medias
) {
}
