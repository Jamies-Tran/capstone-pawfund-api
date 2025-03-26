package com.paw.fund.app.modules.category_management.controller.gender.models;

import lombok.Builder;

@Builder
public record GenderCategoryResponse(
        String code,
        String name
) {
}
