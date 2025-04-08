package com.paw.fund.app.modules.license_management.domain;

import com.paw.fund.app.modules.license_management.domain.section.LicenseSection;
import lombok.Builder;
import lombok.With;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record License(
        Long licenseId,
        String licenseNumber,
        String licenseTypeCode,
        String licenseTypeName,
        LocalDateTime expiryDate,
        String statusCode,
        String statusName,
        @With List<LicenseSection> licenseSections
) {
}
