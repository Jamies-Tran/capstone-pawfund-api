package com.paw.fund.app.modules.license_management.service.section;

import com.paw.fund.app.modules.license_management.domain.section.ILicenseSectionMapper;
import com.paw.fund.app.modules.license_management.domain.section.LicenseSection;
import com.paw.fund.app.modules.license_management.repository.database.section.ILicenseSectionRepository;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LicenseSectionQueryService {
    @NonNull
    ILicenseSectionRepository repository;

    @NonNull
    ILicenseSectionMapper mapper;

    public List<LicenseSection> findAllByLicenseId(Long licenseId) {
        ValidationUtil.validateArgumentNotNull(licenseId);
        return repository.findAllByLicenseId(licenseId).stream()
                .map(mapper::toDto)
                .toList();
    }
}
