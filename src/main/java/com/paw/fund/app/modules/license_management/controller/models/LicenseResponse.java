package com.paw.fund.app.modules.license_management.controller.models;

import com.paw.fund.app.modules.license_management.controller.models.section.LicenseSectionResponse;

import java.time.LocalDateTime;
import java.util.List;

public record LicenseResponse(
        Long licenseId,
        String description,
        String licenseTypeCode,
        String licenseTypeName,
        String statusCode,
        String statusName,
        List<LicenseSectionResponse> licenseSections
) {
}
