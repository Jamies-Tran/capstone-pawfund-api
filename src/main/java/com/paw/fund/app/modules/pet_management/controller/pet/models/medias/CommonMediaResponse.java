package com.paw.fund.app.modules.pet_management.controller.pet.models.medias;

import lombok.Builder;

@Builder
public record CommonMediaResponse(
        Long commonMediaId,
        String url,
        Boolean isThumbnail,
        String mediaTypeCode,
        String mediaTypeName
) {
}
