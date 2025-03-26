package com.paw.fund.app.modules.category_management.controller.account.status.models;

import lombok.Builder;

@Builder
public record AccountStatusCategoryResponse(
        String code,
        String name
) {
}
