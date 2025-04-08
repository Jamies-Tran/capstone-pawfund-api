package com.paw.fund.app.modules.license_management.service;

import com.paw.fund.app.modules.license_management.domain.License;
import com.paw.fund.app.modules.license_management.domain.section.LicenseSection;
import com.paw.fund.app.modules.license_management.domain.usecase.LicenseId;
import com.paw.fund.app.modules.license_management.service.section.LicenseSectionCommandService;
import com.paw.fund.app.modules.license_management.service.section.LicenseSectionQueryService;
import com.paw.fund.app.modules.license_management.service.usecase.ILicenseUseCase;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LicenseUseCaseService implements ILicenseUseCase {
    @NonNull
    LicenseCommandService commandService;

    @NonNull
    LicenseQueryService queryService;

    @NonNull
    LicenseSectionCommandService licenseSectionCommandService;

    @NonNull
    LicenseSectionQueryService licenseSectionQueryService;

    @Override
    @Transactional
    public License createLicense(License license) {
        ValidationUtil.validateNotNullPointerException(license);
        License savedLicense = commandService.save(license);
        List<LicenseSection> licenseSections = licenseSectionCommandService
                .saveAllWithLicenseId(savedLicense.licenseId(), license.licenseSections());

        return savedLicense.withLicenseSections(licenseSections);
    }

    @Override
    public License getLicenseDetail(LicenseId licenseId) {
        ValidationUtil.validateNotNullPointerException(licenseId);
        License foundLicense = queryService.findById(licenseId.value());
        List<LicenseSection> licenseSections = licenseSectionQueryService
                .findAllByLicenseId(foundLicense.licenseId());

        return foundLicense.withLicenseSections(licenseSections);
    }
}
