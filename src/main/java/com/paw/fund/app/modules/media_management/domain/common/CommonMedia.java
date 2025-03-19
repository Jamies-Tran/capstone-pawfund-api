package com.paw.fund.app.modules.media_management.domain.common;

import lombok.Builder;
import lombok.With;

@Builder
public record CommonMedia(
        Long commonMediaId,
        @With Long accountId,
        Long reviewId,
        Long petId,
        Long shelterId,
        String url,
        Boolean isThumbnail,
        @With String mediaTypeCode,
        @With String mediaTypeName
) {
}
