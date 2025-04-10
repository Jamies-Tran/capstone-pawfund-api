package com.paw.fund.app.modules.license_management.service;

import com.paw.fund.app.modules.license_management.domain.ILicenseMapper;
import com.paw.fund.app.modules.license_management.domain.License;
import com.paw.fund.app.modules.license_management.domain.usecase.LicenseSearchCriteria;
import com.paw.fund.app.modules.license_management.repository.database.ILicenseRepository;
import com.paw.fund.app.modules.license_management.repository.database.LicenseEntity;
import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
import com.paw.fund.utils.request.PageRequestCustom;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LicenseQueryService {
    @NonNull
    ILicenseRepository repository;

    @NonNull
    ILicenseMapper mapper;

    public License findById(Long licenseId) {
        ValidationUtil.validateArgumentNotNull(licenseId);
        LicenseEntity foundLicense = repository.findByStatusCodeNotDeletedAndLicenseId(licenseId)
                .orElseThrow(ResourceNotFoundException::new);

        return mapper.toDto(foundLicense);
    }

    public Page<License> findAll(LicenseSearchCriteria searchCriteria, PageRequestCustom requestCustom) {
        ValidationUtil.validateNotNullPointerException(searchCriteria);
        ValidationUtil.validateNotNullPointerException(requestCustom);

        return repository.findAll(searchCriteria, requestCustom.pageRequest())
                .map(mapper::toDto);
    }
}
