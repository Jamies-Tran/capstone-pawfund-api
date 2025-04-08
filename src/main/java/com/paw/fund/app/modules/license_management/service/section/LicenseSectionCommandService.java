package com.paw.fund.app.modules.license_management.service.section;

import com.paw.fund.app.modules.license_management.domain.section.ILicenseSectionMapper;
import com.paw.fund.app.modules.license_management.domain.section.LicenseSection;
import com.paw.fund.app.modules.license_management.repository.database.section.ILicenseSectionRepository;
import com.paw.fund.app.modules.license_management.repository.database.section.LicenseSectionEntity;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LicenseSectionCommandService {
    @NonNull
    ILicenseSectionRepository repository;

    @NonNull
    ILicenseSectionMapper mapper;

    public List<LicenseSection> saveAllWithLicenseId(Long licenseId, List<LicenseSection> licenseSections) {
        List<LicenseSectionEntity> newLicenseSections = licenseSections.stream()
                .map(x -> mapper.toEntity(x.withLicenseId(licenseId)))
                .toList();
        List<LicenseSectionEntity> savedLicenseSections = repository.saveAll(newLicenseSections);

        return savedLicenseSections.stream()
                .map(mapper::toDto)
                .toList();
    }
}
