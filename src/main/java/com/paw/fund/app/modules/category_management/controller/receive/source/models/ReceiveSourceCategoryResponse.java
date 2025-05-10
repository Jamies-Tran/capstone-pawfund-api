package com.paw.fund.app.modules.category_management.controller.receive.source.models;

import lombok.Builder;

@Builder
public record ReceiveSourceCategoryResponse(
        String code,
        String name
) {
}
