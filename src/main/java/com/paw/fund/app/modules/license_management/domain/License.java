package com.paw.fund.app.modules.license_management.domain;

import com.paw.fund.app.modules.license_management.domain.section.LicenseSection;
import lombok.Builder;
import lombok.With;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record License(
        Long licenseId,
        String description,
        String licenseTypeCode,
        String licenseTypeName,
        String statusCode,
        String statusName,
        @With List<LicenseSection> licenseSections
) {
}
