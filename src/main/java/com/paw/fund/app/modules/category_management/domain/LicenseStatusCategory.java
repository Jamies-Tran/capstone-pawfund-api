package com.paw.fund.app.modules.category_management.domain;

import com.paw.fund.enums.ELicenseStatus;
import lombok.Builder;

@Builder
public record LicenseStatusCategory(
        String code,
        String name
) {
    public static LicenseStatusCategory of(ELicenseStatus licenseStatus) {
        return LicenseStatusCategory.builder()
                .code(licenseStatus.getCode())
                .name(licenseStatus.getName())
                .build();
    }
}
