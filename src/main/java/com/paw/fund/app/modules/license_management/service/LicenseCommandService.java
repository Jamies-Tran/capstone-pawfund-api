package com.paw.fund.app.modules.license_management.service;

import com.paw.fund.app.modules.license_management.domain.ILicenseMapper;
import com.paw.fund.app.modules.license_management.domain.License;
import com.paw.fund.app.modules.license_management.repository.database.ILicenseRepository;
import com.paw.fund.app.modules.license_management.repository.database.LicenseEntity;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LicenseCommandService {
    @NonNull
    ILicenseRepository repository;

    @NonNull
    ILicenseMapper mapper;

    public License save(License license) {
        ValidationUtil.validateNotNullPointerException(license);
        LicenseEntity newLicense = mapper.toEntity(license);
        LicenseEntity savedLicense = repository.save(newLicense);

        return mapper.toDto(savedLicense);
    }
}
