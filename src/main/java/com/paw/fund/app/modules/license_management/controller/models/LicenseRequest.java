package com.paw.fund.app.modules.license_management.controller.models;

import com.paw.fund.app.modules.license_management.controller.models.section.LicenseSectionRequest;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record LicenseRequest(
        String licenseNumber,
        String licenseTypeCode,
        String licenseTypeName,
        LocalDateTime expiryDate,
        List<LicenseSectionRequest> licenseSections
) {
}
