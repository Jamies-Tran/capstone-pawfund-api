package com.paw.fund.app.modules.category_management.domain;

import com.paw.fund.enums.ELicenseType;
import lombok.Builder;

@Builder
public record LicenseTypeCategory(
        String code,
        String name
) {
    public static LicenseTypeCategory of(ELicenseType licenseType) {
        return LicenseTypeCategory.builder()
                .code(licenseType.getCode())
                .name(licenseType.getName())
                .build();
    }
}
