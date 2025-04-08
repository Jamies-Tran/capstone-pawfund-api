package com.paw.fund.app.modules.license_management.domain.usecase;

import com.paw.fund.app.modules.license_management.domain.License;
import lombok.Builder;

@Builder
public record LicenseId(Long value) {
    public static LicenseId of(Long value) {
        return LicenseId.builder()
                .value(value)
                .build();
    }
}
