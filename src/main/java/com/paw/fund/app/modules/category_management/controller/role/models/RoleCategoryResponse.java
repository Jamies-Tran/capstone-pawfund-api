package com.paw.fund.app.modules.category_management.controller.role.models;

import lombok.Builder;

@Builder
public record RoleCategoryResponse(
        String code,
        String name
) {
}
