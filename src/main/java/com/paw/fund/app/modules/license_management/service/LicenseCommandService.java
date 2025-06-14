package com.paw.fund.app.modules.license_management.service;


import com.paw.fund.app.modules.license_management.domain.ILicenseMapper;
import com.paw.fund.app.modules.license_management.domain.License;
import com.paw.fund.app.modules.license_management.repository.database.ILicenseRepository;
import com.paw.fund.app.modules.license_management.repository.database.LicenseEntity;
import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
import com.paw.fund.enums.EDeleteStatus;
import com.paw.fund.enums.ELicenseStatus;
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

    public License update(Long licenseId, License license) {
        ValidationUtil.validateArgumentNotNull(licenseId);
        ValidationUtil.validateNotNullPointerException(license);

        return repository.findByStatusCodeNotDeletedAndLicenseId(licenseId)
                .map(x -> {
                    mapper.update(x, license);
                    LicenseEntity savedLicense = repository.save(x);

                    return mapper.toDto(savedLicense);
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    public License updateStatus(Long licenseId, ELicenseStatus status) {
        ValidationUtil.validateArgumentNotNull(licenseId);
        ValidationUtil.validateArgumentNotNull(status);

        return repository.findByStatusCodeNotDeletedAndLicenseId(licenseId)
                .map(x -> {
                    x.setStatusCode(status.getCode());
                    x.setStatusName(status.getName());
                    LicenseEntity savedLicense = repository.save(x);

                    return mapper.toDto(savedLicense);
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    public void delete(Long licenseId) {
        ValidationUtil.validateArgumentNotNull(licenseId);

        repository.findByStatusCodeNotDeletedAndLicenseId(licenseId)
                .map(x -> {
                    x.setStatusCode(EDeleteStatus.DELETED.getCode());
                    x.setStatusName(EDeleteStatus.DELETED.getName());

                    return repository.save(x);
                })
                .orElseThrow(ResourceNotFoundException::new);
    }
}
