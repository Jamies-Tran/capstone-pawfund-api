package com.paw.fund.app.modules.license_management.domain.usecase;

import com.paw.fund.utils.request.PageRequestCustom;
import lombok.Builder;

@Builder
public record LicenseFilter(
        LicenseSearchCriteria searchCriteria,
        PageRequestCustom pageRequestCustom
) {
    public static LicenseFilter of(LicenseSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
        return LicenseFilter.builder()
                .searchCriteria(searchCriteria)
                .pageRequestCustom(pageRequestCustom)
                .build();
    }
}
