package com.paw.fund.app.modules.license_management.service.usecase;

import com.paw.fund.app.modules.license_management.domain.License;
import com.paw.fund.app.modules.license_management.domain.usecase.LicenseFilter;
import com.paw.fund.app.modules.license_management.domain.usecase.LicenseId;
import com.paw.fund.app.modules.license_management.domain.usecase.LicenseUpdate;
import org.springframework.data.domain.Page;

public interface ILicenseUseCase {
    License createLicense(License license);

    License getLicenseDetail(LicenseId licenseId);

    Page<License> getLicenseList(LicenseFilter filter);

    License updateLicense(LicenseUpdate licenseUpdate);

    License activeLicense(LicenseId licenseId);

    License inactiveLicense(LicenseId licenseId);

    void deleteLicense(LicenseId licenseId);
}
