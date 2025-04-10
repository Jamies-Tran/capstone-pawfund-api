package com.paw.fund.app.modules.license_management.domain.usecase;

import com.paw.fund.app.modules.license_management.domain.License;
import lombok.Builder;

@Builder
public record LicenseUpdate(
        Long licenseId,
        License license
) {
    public static LicenseUpdate of(Long licenseId, License license) {
        return LicenseUpdate.builder()
                .licenseId(licenseId)
                .license(license)
                .build();
    }
}
