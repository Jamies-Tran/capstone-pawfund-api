package com.paw.fund.app.modules.category_management.controller.license.models.status;

import lombok.Builder;

@Builder
public record LicenseStatusCategoryResponse(
        String code,
        String name
) {
}
