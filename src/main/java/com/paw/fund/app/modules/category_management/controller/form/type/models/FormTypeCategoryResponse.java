package com.paw.fund.app.modules.category_management.controller.form.type.models;

import lombok.Builder;

@Builder
public record FormTypeCategoryResponse(
        String code,
        String name
) {
}
