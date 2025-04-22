package com.paw.fund.app.modules.shelter_management.controller.api.models;

import lombok.Builder;

@Builder
public record ShelterMediaResponse(
        Long commonMediaId,
        Long shelterId,
        String url,
        Boolean isThumbnail,
        String mediaTypeCode,
        String mediaTypeName
) {
}
