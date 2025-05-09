package com.paw.fund.app.modules.license_management.service;

import com.paw.fund.app.modules.license_management.domain.License;
import com.paw.fund.app.modules.license_management.domain.section.LicenseSection;
import com.paw.fund.app.modules.license_management.domain.usecase.LicenseFilter;
import com.paw.fund.app.modules.license_management.domain.usecase.LicenseId;
import com.paw.fund.app.modules.license_management.domain.usecase.LicenseUpdate;
import com.paw.fund.app.modules.license_management.service.section.LicenseSectionCommandService;
import com.paw.fund.app.modules.license_management.service.section.LicenseSectionQueryService;
import com.paw.fund.app.modules.license_management.service.usecase.ILicenseUseCase;
import com.paw.fund.app.modules.log_management.annotation.CreateAccountActivityLogHelper;
import com.paw.fund.enums.EAccountAction;
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
    @CreateAccountActivityLogHelper(action = EAccountAction.CREATED, isCurrentLogin = true)
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
    @CreateAccountActivityLogHelper(action = EAccountAction.UPDATE_LICENSE, isCurrentLogin = true)
    public License updateLicense(LicenseUpdate licenseUpdate) {
        ValidationUtil.validateNotNullPointerException(licenseUpdate);
        License updatedLicense = commandService.update(licenseUpdate.licenseId(), licenseUpdate.license());
        List<LicenseSection> licenseSections = licenseSectionCommandService.updateAllByLicenseId(updatedLicense.licenseId(),
                licenseUpdate.license().licenseSections());

        return updatedLicense.withLicenseSections(licenseSections);
    }

    @Override
    @Transactional
    @CreateAccountActivityLogHelper(action = EAccountAction.ACTIVE_LICENSE, isCurrentLogin = true)
    public License activeLicense(LicenseId licenseId) {
        return commandService.updateStatus(licenseId.value(), ELicenseStatus.ACTIVE);
    }

    @Override
    @Transactional
    @CreateAccountActivityLogHelper(action = EAccountAction.INACTIVE_LICENSE, isCurrentLogin = true)
    public License inactiveLicense(LicenseId licenseId) {
        return commandService.updateStatus(licenseId.value(), ELicenseStatus.INACTIVE);
    }

    @Override
    @Transactional
    @CreateAccountActivityLogHelper(action = EAccountAction.DELETE_ACCOUNT, isCurrentLogin = true)
    public void deleteLicense(LicenseId licenseId) {
        commandService.delete(licenseId.value());
    }
}
