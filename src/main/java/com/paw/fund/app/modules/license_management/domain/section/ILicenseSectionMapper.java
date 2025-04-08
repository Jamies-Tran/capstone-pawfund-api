package com.paw.fund.app.modules.license_management.domain.section;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paw.fund.app.modules.license_management.repository.database.section.LicenseSectionEntity;
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
public interface ILicenseSectionMapper {
    @Mapping(target = "sectionContent", expression = "java(toByte(dto.sectionContent()))")
    LicenseSectionEntity toEntity(LicenseSection dto);

    @Mapping(target = "sectionContent", expression = "java(toContent(entity.getSectionContent()))")
    LicenseSection toDto(LicenseSectionEntity entity);

    default byte[] toByte(List<SectionContent> sectionContents) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsBytes(sectionContents);
        } catch (Exception e) {
            throw new RuntimeException("Error converting section contents to byte array", e);
        }
    }

    default List<SectionContent> toContent(byte[] bytes) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(bytes, new TypeReference<List<SectionContent>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Error converting section contents to byte array", e);
        }
    }
}
