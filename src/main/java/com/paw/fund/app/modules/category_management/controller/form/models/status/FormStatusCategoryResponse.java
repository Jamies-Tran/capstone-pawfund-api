package com.paw.fund.app.modules.category_management.controller.form.models.status;

import lombok.Builder;

@Builder
public record FormStatusCategoryResponse(
        String code,
        String name
) {
}
