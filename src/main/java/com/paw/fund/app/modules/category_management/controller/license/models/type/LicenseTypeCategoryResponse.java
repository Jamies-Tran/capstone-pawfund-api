package com.paw.fund.app.modules.category_management.controller.license.models.type;

import lombok.Builder;

@Builder
public record LicenseTypeCategoryResponse(
        String code,
        String name
) {
}
