package com.paw.fund.app.modules.license_management.domain.usecase;

import com.paw.fund.utils.validation.ValidationUtil;
import lombok.Builder;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record LicenseSearchCriteria(String search,
                                    List<LocalDateTime> timeRange,
                                    List<String> licenseTypeCodes,
                                    List<String> statusCodes) {

    public static LicenseSearchCriteria of(List<LocalDateTime> timeRange,
                                           List<String> licenseTypeCodes,
                                           List<String> statusCodes) {
        return LicenseSearchCriteria.builder()
                .timeRange(ValidationUtil.validateNotNullOrDefaultTimeRange(timeRange))
                .licenseTypeCodes(licenseTypeCodes)
                .statusCodes(statusCodes)
                .build();
    }

    public Boolean isSearchEmptyOrNull() {
        return !StringUtils.hasText(search);
    }

    public Boolean isLicenseTypeCodesEmptyOrNull() {
        return CollectionUtils.isEmpty(licenseTypeCodes);
    }

    public Boolean isStatusCodesEmptyOrNull() {
        return CollectionUtils.isEmpty(statusCodes);
    }

}
