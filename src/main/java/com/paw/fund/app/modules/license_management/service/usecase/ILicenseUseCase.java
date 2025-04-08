package com.paw.fund.app.modules.license_management.service.usecase;

import com.paw.fund.app.modules.license_management.domain.License;
import com.paw.fund.app.modules.license_management.domain.usecase.LicenseId;

public interface ILicenseUseCase {
    License createLicense(License license);

    License getLicenseDetail(LicenseId licenseId);
}
