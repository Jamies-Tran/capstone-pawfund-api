package com.paw.fund.app.modules.category_management.controller.form.models.type;

import lombok.Builder;

@Builder
public record FormTypeCategoryResponse(
        String code,
        String name
) {
}
