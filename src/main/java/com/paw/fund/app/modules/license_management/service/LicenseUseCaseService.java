package com.paw.fund.app.modules.license_management.service;

import com.paw.fund.app.modules.license_management.domain.License;
import com.paw.fund.app.modules.license_management.domain.section.LicenseSection;
import com.paw.fund.app.modules.license_management.domain.usecase.LicenseFilter;
import com.paw.fund.app.modules.license_management.domain.usecase.LicenseId;
import com.paw.fund.app.modules.license_management.domain.usecase.LicenseUpdate;
import com.paw.fund.app.modules.license_management.service.section.LicenseSectionCommandService;
import com.paw.fund.app.modules.license_management.service.section.LicenseSectionQueryService;
import com.paw.fund.app.modules.license_management.service.usecase.ILicenseUseCase;
import com.paw.fund.app.modules.log_management.annotation.LogAction;
import com.paw.fund.enums.EAction;
import com.paw.fund.enums.ELicenseStatus;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
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
    @LogAction(action = EAction.CREATED, isCurrentLogin = true)
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

    @Override
    public Page<License> getLicenseList(LicenseFilter filter) {
        return queryService.findAll(filter.searchCriteria(), filter.pageRequestCustom());
    }

    @Override
    @Transactional
    @LogAction(action = EAction.UPDATE_LICENSE, isCurrentLogin = true)
    public License updateLicense(LicenseUpdate licenseUpdate) {
        ValidationUtil.validateNotNullPointerException(licenseUpdate);
        License updatedLicense = commandService.update(licenseUpdate.licenseId(), licenseUpdate.license());
        List<LicenseSection> licenseSections = licenseSectionCommandService.updateAllByLicenseId(updatedLicense.licenseId(),
                licenseUpdate.license().licenseSections());

        return updatedLicense.withLicenseSections(licenseSections);
    }

    @Override
    @Transactional
    @LogAction(action = EAction.ACTIVE_LICENSE, isCurrentLogin = true)
    public License activeLicense(LicenseId licenseId) {
        return commandService.updateStatus(licenseId.value(), ELicenseStatus.ACTIVE);
    }

    @Override
    @Transactional
    @LogAction(action = EAction.INACTIVE_LICENSE, isCurrentLogin = true)
    public License inactiveLicense(LicenseId licenseId) {
        return commandService.updateStatus(licenseId.value(), ELicenseStatus.INACTIVE);
    }

    @Override
    @Transactional
    @LogAction(action = EAction.DELETE_ACCOUNT, isCurrentLogin = true)
    public void deleteLicense(LicenseId licenseId) {
        commandService.delete(licenseId.value());
    }
}
