package com.paw.fund.app.modules.license_management.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paw.fund.app.modules.license_management.domain.section.ILicenseSectionMapper;
import com.paw.fund.app.modules.license_management.domain.section.SectionContent;
import com.paw.fund.app.modules.license_management.repository.database.LicenseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ILicenseMapper {
    LicenseEntity toEntity(License dto);

    License toDto(LicenseEntity entity);
}
