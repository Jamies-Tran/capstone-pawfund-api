package com.paw.fund.app.modules.shelter_management.controller.api.models;

import lombok.Builder;

@Builder
public record ShelterMediaRequest(
        String url,
        Boolean isThumbnail
) {
}
