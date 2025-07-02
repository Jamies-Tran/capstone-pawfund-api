package com.paw.fund.app.modules.account_management.controller.models.medias;

import com.paw.fund.common.category.CategoryResponse;
import lombok.Builder;

@Builder
public record CommonMediaResponse(
        Long commonMediaId,
        String url,
        Boolean isThumbnail,
        CategoryResponse mediaType
) {
}
