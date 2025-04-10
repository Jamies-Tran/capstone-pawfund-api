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
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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

    public List<LicenseSection> updateAllByLicenseId(Long licenseId, List<LicenseSection> licenseSections) {
        List<LicenseSectionEntity> foundLicenseSection = repository.findAllByLicenseId(licenseId);

        List<Long> newLicenseSectionIds = licenseSections.stream()
                .map(LicenseSection::licenseSectionId)
                .toList();
        List<Long> deletedIds = foundLicenseSection.stream()
                .map(LicenseSectionEntity::getLicenseSectionId)
                .filter(x -> !newLicenseSectionIds.contains(x))
                .toList();
        repository.deleteAllById(deletedIds);

        Map<Long, LicenseSectionEntity> foundLicenseSectionMap = foundLicenseSection.stream()
                .collect(Collectors.toMap(LicenseSectionEntity::getLicenseSectionId, x -> x));
        List<LicenseSectionEntity> newLicenseSections = licenseSections.stream()
                .map(x -> {
                    LicenseSectionEntity newLicenseSection;
                    if(Objects.isNull(x.licenseSectionId())) {
                        newLicenseSection = mapper.toEntity(x.withLicenseId(licenseId));
                    } else {
                        newLicenseSection = foundLicenseSectionMap.computeIfAbsent(x.licenseSectionId(), _ -> {
                            LicenseSectionEntity altLicense = mapper.toEntity(x.withLicenseId(licenseId));
                            altLicense.setLicenseSectionId(null);

                            return altLicense;
                        });

                        mapper.update(newLicenseSection, x);
                    }

                    return newLicenseSection;
                })
                .toList();
        List<LicenseSectionEntity> savedLicenseSections = repository.saveAll(newLicenseSections);

        return savedLicenseSections.stream().map(mapper::toDto).toList();

    }
}
