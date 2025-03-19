package com.paw.fund.app.modules.account_management.controller.models.medias;

import lombok.Builder;

@Builder
public record CommonMediaResponse(
        String url,
        Boolean isThumbnail,
        String mediaTypeCode,
        String mediaTypeName
) {
}
